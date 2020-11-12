import { __decorate, __metadata, __param } from "tslib";
import { Inject, Injectable, Injector } from '@angular/core';
import { switchMap } from 'rxjs/operators';
import { NbAuthService } from '../auth.service';
import { NB_AUTH_INTERCEPTOR_HEADER } from '../../auth.options';
let NbAuthSimpleInterceptor = class NbAuthSimpleInterceptor {
    constructor(injector, headerName = 'Authorization') {
        this.injector = injector;
        this.headerName = headerName;
    }
    intercept(req, next) {
        return this.authService.getToken()
            .pipe(switchMap((token) => {
            if (token && token.getValue()) {
                req = req.clone({
                    setHeaders: {
                        [this.headerName]: token.getValue(),
                    },
                });
            }
            return next.handle(req);
        }));
    }
    get authService() {
        return this.injector.get(NbAuthService);
    }
};
NbAuthSimpleInterceptor = __decorate([
    Injectable(),
    __param(1, Inject(NB_AUTH_INTERCEPTOR_HEADER)),
    __metadata("design:paramtypes", [Injector, String])
], NbAuthSimpleInterceptor);
export { NbAuthSimpleInterceptor };
//# sourceMappingURL=simple-interceptor.js.map