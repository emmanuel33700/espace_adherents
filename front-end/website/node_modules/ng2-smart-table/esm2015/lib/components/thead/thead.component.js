import { __decorate, __metadata } from "tslib";
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Grid } from '../../lib/grid';
import { DataSource } from '../../lib/data-source/data-source';
let Ng2SmartTableTheadComponent = class Ng2SmartTableTheadComponent {
    constructor() {
        this.sort = new EventEmitter();
        this.selectAllRows = new EventEmitter();
        this.create = new EventEmitter();
        this.filter = new EventEmitter();
    }
    ngOnChanges() {
        this.isHideHeader = this.grid.getSetting('hideHeader');
        this.isHideSubHeader = this.grid.getSetting('hideSubHeader');
    }
};
__decorate([
    Input(),
    __metadata("design:type", Grid)
], Ng2SmartTableTheadComponent.prototype, "grid", void 0);
__decorate([
    Input(),
    __metadata("design:type", DataSource)
], Ng2SmartTableTheadComponent.prototype, "source", void 0);
__decorate([
    Input(),
    __metadata("design:type", Boolean)
], Ng2SmartTableTheadComponent.prototype, "isAllSelected", void 0);
__decorate([
    Input(),
    __metadata("design:type", EventEmitter)
], Ng2SmartTableTheadComponent.prototype, "createConfirm", void 0);
__decorate([
    Output(),
    __metadata("design:type", Object)
], Ng2SmartTableTheadComponent.prototype, "sort", void 0);
__decorate([
    Output(),
    __metadata("design:type", Object)
], Ng2SmartTableTheadComponent.prototype, "selectAllRows", void 0);
__decorate([
    Output(),
    __metadata("design:type", Object)
], Ng2SmartTableTheadComponent.prototype, "create", void 0);
__decorate([
    Output(),
    __metadata("design:type", Object)
], Ng2SmartTableTheadComponent.prototype, "filter", void 0);
Ng2SmartTableTheadComponent = __decorate([
    Component({
        selector: '[ng2-st-thead]',
        template: "<tr ng2-st-thead-titles-row *ngIf=\"!isHideHeader\"\n                            class=\"ng2-smart-titles\"\n                            [grid]=\"grid\"\n                            [isAllSelected]=\"isAllSelected\"\n                            [source]=\"source\"\n                            (sort)=\"sort.emit($event)\"\n                            (selectAllRows)=\"selectAllRows.emit($event)\">\n</tr>\n\n<tr ng2-st-thead-filters-row *ngIf=\"!isHideSubHeader\"\n                              class=\"ng2-smart-filters\"\n                              [grid]=\"grid\"\n                              [source]=\"source\"\n                              (create)=\"create.emit($event)\"\n                              (filter)=\"filter.emit($event)\">\n</tr>\n\n<tr ng2-st-thead-form-row *ngIf=\"grid.createFormShown\"\n                          [grid]=\"grid\"\n                          [createConfirm]=\"createConfirm\">\n</tr>\n"
    })
], Ng2SmartTableTheadComponent);
export { Ng2SmartTableTheadComponent };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoidGhlYWQuY29tcG9uZW50LmpzIiwic291cmNlUm9vdCI6Im5nOi8vbmcyLXNtYXJ0LXRhYmxlLyIsInNvdXJjZXMiOlsibGliL2NvbXBvbmVudHMvdGhlYWQvdGhlYWQuY29tcG9uZW50LnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxPQUFPLEVBQUMsU0FBUyxFQUFFLEtBQUssRUFBRSxNQUFNLEVBQUUsWUFBWSxFQUFZLE1BQU0sZUFBZSxDQUFDO0FBRWhGLE9BQU8sRUFBRSxJQUFJLEVBQUUsTUFBTSxnQkFBZ0IsQ0FBQztBQUN0QyxPQUFPLEVBQUUsVUFBVSxFQUFFLE1BQU0sbUNBQW1DLENBQUM7QUFNL0QsSUFBYSwyQkFBMkIsR0FBeEMsTUFBYSwyQkFBMkI7SUFBeEM7UUFPYyxTQUFJLEdBQUcsSUFBSSxZQUFZLEVBQU8sQ0FBQztRQUMvQixrQkFBYSxHQUFHLElBQUksWUFBWSxFQUFPLENBQUM7UUFDeEMsV0FBTSxHQUFHLElBQUksWUFBWSxFQUFPLENBQUM7UUFDakMsV0FBTSxHQUFHLElBQUksWUFBWSxFQUFPLENBQUM7SUFTL0MsQ0FBQztJQUpDLFdBQVc7UUFDUCxJQUFJLENBQUMsWUFBWSxHQUFHLElBQUksQ0FBQyxJQUFJLENBQUMsVUFBVSxDQUFDLFlBQVksQ0FBQyxDQUFDO1FBQ3ZELElBQUksQ0FBQyxlQUFlLEdBQUcsSUFBSSxDQUFDLElBQUksQ0FBQyxVQUFVLENBQUMsZUFBZSxDQUFDLENBQUM7SUFDL0QsQ0FBQztDQUNKLENBQUE7QUFqQlk7SUFBUixLQUFLLEVBQUU7OEJBQU8sSUFBSTt5REFBQztBQUNYO0lBQVIsS0FBSyxFQUFFOzhCQUFTLFVBQVU7MkRBQUM7QUFDbkI7SUFBUixLQUFLLEVBQUU7O2tFQUF3QjtBQUN2QjtJQUFSLEtBQUssRUFBRTs4QkFBZ0IsWUFBWTtrRUFBTTtBQUVoQztJQUFULE1BQU0sRUFBRTs7eURBQWdDO0FBQy9CO0lBQVQsTUFBTSxFQUFFOztrRUFBeUM7QUFDeEM7SUFBVCxNQUFNLEVBQUU7OzJEQUFrQztBQUNqQztJQUFULE1BQU0sRUFBRTs7MkRBQWtDO0FBVmxDLDJCQUEyQjtJQUp2QyxTQUFTLENBQUM7UUFDUCxRQUFRLEVBQUUsZ0JBQWdCO1FBQzFCLGc3QkFBcUM7S0FDeEMsQ0FBQztHQUNXLDJCQUEyQixDQW1CdkM7U0FuQlksMkJBQTJCIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHtDb21wb25lbnQsIElucHV0LCBPdXRwdXQsIEV2ZW50RW1pdHRlciwgT25DaGFuZ2VzfSBmcm9tICdAYW5ndWxhci9jb3JlJztcblxuaW1wb3J0IHsgR3JpZCB9IGZyb20gJy4uLy4uL2xpYi9ncmlkJztcbmltcG9ydCB7IERhdGFTb3VyY2UgfSBmcm9tICcuLi8uLi9saWIvZGF0YS1zb3VyY2UvZGF0YS1zb3VyY2UnO1xuXG5AQ29tcG9uZW50KHtcbiAgICBzZWxlY3RvcjogJ1tuZzItc3QtdGhlYWRdJyxcbiAgICB0ZW1wbGF0ZVVybDogJy4vdGhlYWQuY29tcG9uZW50Lmh0bWwnLFxufSlcbmV4cG9ydCBjbGFzcyBOZzJTbWFydFRhYmxlVGhlYWRDb21wb25lbnQgaW1wbGVtZW50cyBPbkNoYW5nZXMge1xuXG4gICAgQElucHV0KCkgZ3JpZDogR3JpZDtcbiAgICBASW5wdXQoKSBzb3VyY2U6IERhdGFTb3VyY2U7XG4gICAgQElucHV0KCkgaXNBbGxTZWxlY3RlZDogYm9vbGVhbjtcbiAgICBASW5wdXQoKSBjcmVhdGVDb25maXJtOiBFdmVudEVtaXR0ZXI8YW55PjtcblxuICAgIEBPdXRwdXQoKSBzb3J0ID0gbmV3IEV2ZW50RW1pdHRlcjxhbnk+KCk7XG4gICAgQE91dHB1dCgpIHNlbGVjdEFsbFJvd3MgPSBuZXcgRXZlbnRFbWl0dGVyPGFueT4oKTtcbiAgICBAT3V0cHV0KCkgY3JlYXRlID0gbmV3IEV2ZW50RW1pdHRlcjxhbnk+KCk7XG4gICAgQE91dHB1dCgpIGZpbHRlciA9IG5ldyBFdmVudEVtaXR0ZXI8YW55PigpO1xuXG4gICAgaXNIaWRlSGVhZGVyOiBib29sZWFuO1xuICAgIGlzSGlkZVN1YkhlYWRlcjogYm9vbGVhbjtcblxuICBuZ09uQ2hhbmdlcygpIHtcbiAgICAgIHRoaXMuaXNIaWRlSGVhZGVyID0gdGhpcy5ncmlkLmdldFNldHRpbmcoJ2hpZGVIZWFkZXInKTtcbiAgICAgIHRoaXMuaXNIaWRlU3ViSGVhZGVyID0gdGhpcy5ncmlkLmdldFNldHRpbmcoJ2hpZGVTdWJIZWFkZXInKTtcbiAgICB9XG59XG4iXX0=