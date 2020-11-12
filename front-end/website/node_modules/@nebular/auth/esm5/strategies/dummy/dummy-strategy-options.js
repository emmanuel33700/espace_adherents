import { __extends } from "tslib";
/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { NbAuthStrategyOptions } from '../auth-strategy-options';
import { NbAuthSimpleToken } from '../../services/token/token';
var NbDummyAuthStrategyOptions = /** @class */ (function (_super) {
    __extends(NbDummyAuthStrategyOptions, _super);
    function NbDummyAuthStrategyOptions() {
        var _this = _super !== null && _super.apply(this, arguments) || this;
        _this.token = {
            class: NbAuthSimpleToken,
        };
        _this.delay = 1000;
        _this.alwaysFail = false;
        return _this;
    }
    return NbDummyAuthStrategyOptions;
}(NbAuthStrategyOptions));
export { NbDummyAuthStrategyOptions };
export var dummyStrategyOptions = new NbDummyAuthStrategyOptions();
//# sourceMappingURL=dummy-strategy-options.js.map