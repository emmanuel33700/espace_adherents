import { __decorate, __extends } from "tslib";
import { Injectable } from '@angular/core';
import { of as observableOf } from 'rxjs';
import { delay } from 'rxjs/operators';
import { NbAuthStrategy } from '../auth-strategy';
import { NbAuthResult } from '../../services/auth-result';
import { dummyStrategyOptions } from './dummy-strategy-options';
/**
 * Dummy auth strategy. Could be useful for auth setup when backend is not available yet.
 *
 *
 * Strategy settings.
 *
 * ```ts
 * export class NbDummyAuthStrategyOptions extends NbAuthStrategyOptions {
 *   name = 'dummy';
 *   token = {
 *     class: NbAuthSimpleToken,
 *   };
 *   delay? = 1000;
 *   alwaysFail? = false;
 * }
 * ```
 */
var NbDummyAuthStrategy = /** @class */ (function (_super) {
    __extends(NbDummyAuthStrategy, _super);
    function NbDummyAuthStrategy() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.defaultOptions = dummyStrategyOptions;
        return _this;
    }
    NbDummyAuthStrategy_1 = NbDummyAuthStrategy;
    NbDummyAuthStrategy.setup = function (options) {
        return [NbDummyAuthStrategy_1, options];
    };
    NbDummyAuthStrategy.prototype.authenticate = function (data) {
        return observableOf(this.createDummyResult(data))
            .pipe(delay(this.getOption('delay')));
    };
    NbDummyAuthStrategy.prototype.register = function (data) {
        return observableOf(this.createDummyResult(data))
            .pipe(delay(this.getOption('delay')));
    };
    NbDummyAuthStrategy.prototype.requestPassword = function (data) {
        return observableOf(this.createDummyResult(data))
            .pipe(delay(this.getOption('delay')));
    };
    NbDummyAuthStrategy.prototype.resetPassword = function (data) {
        return observableOf(this.createDummyResult(data))
            .pipe(delay(this.getOption('delay')));
    };
    NbDummyAuthStrategy.prototype.logout = function (data) {
        return observableOf(this.createDummyResult(data))
            .pipe(delay(this.getOption('delay')));
    };
    NbDummyAuthStrategy.prototype.refreshToken = function (data) {
        return observableOf(this.createDummyResult(data))
            .pipe(delay(this.getOption('delay')));
    };
    NbDummyAuthStrategy.prototype.createDummyResult = function (data) {
        if (this.getOption('alwaysFail')) {
            return new NbAuthResult(false, this.createFailResponse(data), null, ['Something went wrong.']);
        }
        try {
            var token = this.createToken('test token', true);
            return new NbAuthResult(true, this.createSuccessResponse(data), '/', [], ['Successfully logged in.'], token);
        }
        catch (err) {
            return new NbAuthResult(false, this.createFailResponse(data), null, [err.message]);
        }
    };
    var NbDummyAuthStrategy_1;
    NbDummyAuthStrategy = NbDummyAuthStrategy_1 = __decorate([
        Injectable()
    ], NbDummyAuthStrategy);
    return NbDummyAuthStrategy;
}(NbAuthStrategy));
export { NbDummyAuthStrategy };
//# sourceMappingURL=dummy-strategy.js.map