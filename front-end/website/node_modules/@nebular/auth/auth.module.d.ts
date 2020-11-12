import { Injector, ModuleWithProviders } from '@angular/core';
import { HttpRequest } from '@angular/common/http';
import { NbAuthTokenClass } from './services/token/token';
import { NbAuthStrategy } from './strategies/auth-strategy';
import { NbAuthOptions } from './auth.options';
import * as ɵngcc0 from '@angular/core';
import * as ɵngcc1 from './components/auth.component';
import * as ɵngcc2 from './components/auth-block/auth-block.component';
import * as ɵngcc3 from './components/login/login.component';
import * as ɵngcc4 from './components/register/register.component';
import * as ɵngcc5 from './components/request-password/request-password.component';
import * as ɵngcc6 from './components/reset-password/reset-password.component';
import * as ɵngcc7 from './components/logout/logout.component';
import * as ɵngcc8 from '@angular/common';
import * as ɵngcc9 from '@nebular/theme';
import * as ɵngcc10 from '@angular/router';
import * as ɵngcc11 from '@angular/forms';
export declare function nbStrategiesFactory(options: NbAuthOptions, injector: Injector): NbAuthStrategy[];
export declare function nbTokensFactory(strategies: NbAuthStrategy[]): NbAuthTokenClass[];
export declare function nbOptionsFactory(options: any): any;
export declare function nbNoOpInterceptorFilter(req: HttpRequest<any>): boolean;
export declare class NbAuthModule {
    static forRoot(nbAuthOptions?: NbAuthOptions): ModuleWithProviders<NbAuthModule>;
    static ɵmod: ɵngcc0.ɵɵNgModuleDefWithMeta<NbAuthModule, [typeof ɵngcc1.NbAuthComponent, typeof ɵngcc2.NbAuthBlockComponent, typeof ɵngcc3.NbLoginComponent, typeof ɵngcc4.NbRegisterComponent, typeof ɵngcc5.NbRequestPasswordComponent, typeof ɵngcc6.NbResetPasswordComponent, typeof ɵngcc7.NbLogoutComponent], [typeof ɵngcc8.CommonModule, typeof ɵngcc9.NbLayoutModule, typeof ɵngcc9.NbCardModule, typeof ɵngcc9.NbCheckboxModule, typeof ɵngcc9.NbAlertModule, typeof ɵngcc9.NbInputModule, typeof ɵngcc9.NbButtonModule, typeof ɵngcc10.RouterModule, typeof ɵngcc11.FormsModule, typeof ɵngcc9.NbIconModule], [typeof ɵngcc1.NbAuthComponent, typeof ɵngcc2.NbAuthBlockComponent, typeof ɵngcc3.NbLoginComponent, typeof ɵngcc4.NbRegisterComponent, typeof ɵngcc5.NbRequestPasswordComponent, typeof ɵngcc6.NbResetPasswordComponent, typeof ɵngcc7.NbLogoutComponent]>;
    static ɵinj: ɵngcc0.ɵɵInjectorDef<NbAuthModule>;
}

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiYXV0aC5tb2R1bGUuZC50cyIsInNvdXJjZXMiOlsiYXV0aC5tb2R1bGUuZC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiQUFBQTs7Ozs7Ozs7Ozs7Ozs7Ozs7QUFLQTs7Ozs7Ozs7QUFNQSIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IEluamVjdG9yLCBNb2R1bGVXaXRoUHJvdmlkZXJzIH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XG5pbXBvcnQgeyBIdHRwUmVxdWVzdCB9IGZyb20gJ0Bhbmd1bGFyL2NvbW1vbi9odHRwJztcbmltcG9ydCB7IE5iQXV0aFRva2VuQ2xhc3MgfSBmcm9tICcuL3NlcnZpY2VzL3Rva2VuL3Rva2VuJztcbmltcG9ydCB7IE5iQXV0aFN0cmF0ZWd5IH0gZnJvbSAnLi9zdHJhdGVnaWVzL2F1dGgtc3RyYXRlZ3knO1xuaW1wb3J0IHsgTmJBdXRoT3B0aW9ucyB9IGZyb20gJy4vYXV0aC5vcHRpb25zJztcbmV4cG9ydCBkZWNsYXJlIGZ1bmN0aW9uIG5iU3RyYXRlZ2llc0ZhY3Rvcnkob3B0aW9uczogTmJBdXRoT3B0aW9ucywgaW5qZWN0b3I6IEluamVjdG9yKTogTmJBdXRoU3RyYXRlZ3lbXTtcbmV4cG9ydCBkZWNsYXJlIGZ1bmN0aW9uIG5iVG9rZW5zRmFjdG9yeShzdHJhdGVnaWVzOiBOYkF1dGhTdHJhdGVneVtdKTogTmJBdXRoVG9rZW5DbGFzc1tdO1xuZXhwb3J0IGRlY2xhcmUgZnVuY3Rpb24gbmJPcHRpb25zRmFjdG9yeShvcHRpb25zOiBhbnkpOiBhbnk7XG5leHBvcnQgZGVjbGFyZSBmdW5jdGlvbiBuYk5vT3BJbnRlcmNlcHRvckZpbHRlcihyZXE6IEh0dHBSZXF1ZXN0PGFueT4pOiBib29sZWFuO1xuZXhwb3J0IGRlY2xhcmUgY2xhc3MgTmJBdXRoTW9kdWxlIHtcbiAgICBzdGF0aWMgZm9yUm9vdChuYkF1dGhPcHRpb25zPzogTmJBdXRoT3B0aW9ucyk6IE1vZHVsZVdpdGhQcm92aWRlcnM8TmJBdXRoTW9kdWxlPjtcbn1cbiJdfQ==