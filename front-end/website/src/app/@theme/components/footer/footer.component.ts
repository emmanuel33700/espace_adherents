import { Component } from '@angular/core';

@Component({
  selector: 'ngx-footer',
  styleUrls: ['./footer.component.scss'],
  template: `
    <span class="created-by">Version de démonstration - Projet opensource sous <a href="https://github.com/emmanuel33700/espace_adherents">Github</a></span>
    <div class="socials">
      <a href="https://github.com/emmanuel33700/espace_adherents" target="_blank" class="ion ion-social-github"></a>
    </div>
  `,
})
export class FooterComponent {
}
