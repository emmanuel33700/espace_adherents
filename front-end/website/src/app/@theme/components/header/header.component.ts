import { Component, OnDestroy, OnInit } from '@angular/core';
import { NbMediaBreakpointsService, NbMenuService, NbSidebarService, NbThemeService } from '@nebular/theme';

import {UserData} from '../../../@core/data/users';
import { LayoutService } from '../../../@core/utils';
import {map, switchMap, takeUntil} from 'rxjs/operators';
import { Subject } from 'rxjs';
import {Router} from '@angular/router';
import {AdherentService} from '../../../../api/generated/services/adherent.service';
import {Adherent} from '../../../../api/generated/models/adherent';
import {NbAuthService, NbAuthToken} from '@nebular/auth';

@Component({
  selector: 'ngx-header',
  styleUrls: ['./header.component.scss'],
  templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();
  userPictureOnly: boolean = false;

  public adherent: Adherent;
  public idAdherents: number = 0;


  user = { name: 'xxx', picture: 'assets/images/nick.png' };

  currentTheme = 'default';

  userMenu = [ { title: 'Mes infos', link: '/pages/profile/modifier'}, { title: 'Déconnexion', link: '/auth/login' } ];

  constructor(private sidebarService: NbSidebarService,
              private menuService: NbMenuService,
              private themeService: NbThemeService,
              private userService: UserData,
              private layoutService: LayoutService,
              private breakpointService: NbMediaBreakpointsService,
              private adherentService: AdherentService,
              private authService: NbAuthService) {
  }

  ngOnInit() {
    this.currentTheme = this.themeService.currentTheme;


    this.authService.getToken()
      .subscribe((token: NbAuthToken) => {
        if (token && token.getValue()) {
          this.idAdherents = Number(this.getClaims(token.getValue()));
        }
      });


    // recuperation des informations de l'adhérents
    this.adherentService.getAdherent({
      idadh: this.idAdherents,
    }).subscribe(
      (data) => {
        this.adherent = data;
      },
      (error) => {
        console.info(error);
      },
      () => {
        localStorage.setItem('adherent', JSON.stringify(this.adherent));
        this.user.name = this.adherent.nom + ' ' + this.adherent.prenom;

        // TODO a revoir
        this.user.picture = 'assets/images/kate.png';
      });



    const { xl } = this.breakpointService.getBreakpointsMap();
    this.themeService.onMediaQueryChange()
      .pipe(
        map(([, currentBreakpoint]) => currentBreakpoint.width < xl),
        takeUntil(this.destroy$),
      )
      .subscribe((isLessThanXl: boolean) => this.userPictureOnly = isLessThanXl);

    this.themeService.onThemeChange()
      .pipe(
        map(({ name }) => name),
        takeUntil(this.destroy$),
      )
      .subscribe(themeName => this.currentTheme = themeName);
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  changeTheme(themeName: string) {
    this.themeService.changeTheme(themeName);
  }

  toggleSidebar(): boolean {
    this.sidebarService.toggle(true, 'menu-sidebar');
    this.layoutService.changeLayoutSize();

    return false;
  }

  navigateHome() {
    this.menuService.navigateHome();
    return false;
  }

  getClaims(rawToken: string): string {

    console.info(rawToken);
    const obj = JSON.parse(rawToken);
    return obj.idAdherent ;
  }
}
