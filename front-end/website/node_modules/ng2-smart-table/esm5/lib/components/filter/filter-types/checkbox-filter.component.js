import { __decorate, __extends, __metadata } from "tslib";
import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { DefaultFilter } from './default-filter';
import { debounceTime } from 'rxjs/operators';
var CheckboxFilterComponent = /** @class */ (function (_super) {
    __extends(CheckboxFilterComponent, _super);
    function CheckboxFilterComponent() {
        var _this = _super.call(this) || this;
        _this.filterActive = false;
        _this.inputControl = new FormControl();
        return _this;
    }
    CheckboxFilterComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.changesSubscription = this.inputControl.valueChanges
            .pipe(debounceTime(this.delay))
            .subscribe(function (checked) {
            _this.filterActive = true;
            var trueVal = (_this.column.getFilterConfig() && _this.column.getFilterConfig().true) || true;
            var falseVal = (_this.column.getFilterConfig() && _this.column.getFilterConfig().false) || false;
            _this.query = checked ? trueVal : falseVal;
            _this.setFilter();
        });
    };
    CheckboxFilterComponent.prototype.resetFilter = function (event) {
        event.preventDefault();
        this.query = '';
        this.inputControl.setValue(false, { emitEvent: false });
        this.filterActive = false;
        this.setFilter();
    };
    CheckboxFilterComponent = __decorate([
        Component({
            selector: 'checkbox-filter',
            template: "\n    <input type=\"checkbox\" [formControl]=\"inputControl\" [ngClass]=\"inputClass\" class=\"form-control\">\n    <a href=\"#\" *ngIf=\"filterActive\"\n                (click)=\"resetFilter($event)\">{{column.getFilterConfig()?.resetText || 'reset'}}</a>\n  "
        }),
        __metadata("design:paramtypes", [])
    ], CheckboxFilterComponent);
    return CheckboxFilterComponent;
}(DefaultFilter));
export { CheckboxFilterComponent };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiY2hlY2tib3gtZmlsdGVyLmNvbXBvbmVudC5qcyIsInNvdXJjZVJvb3QiOiJuZzovL25nMi1zbWFydC10YWJsZS8iLCJzb3VyY2VzIjpbImxpYi9jb21wb25lbnRzL2ZpbHRlci9maWx0ZXItdHlwZXMvY2hlY2tib3gtZmlsdGVyLmNvbXBvbmVudC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUEsT0FBTyxFQUFFLFNBQVMsRUFBVSxNQUFNLGVBQWUsQ0FBQztBQUNsRCxPQUFPLEVBQUUsV0FBVyxFQUFFLE1BQU0sZ0JBQWdCLENBQUM7QUFFN0MsT0FBTyxFQUFFLGFBQWEsRUFBRSxNQUFNLGtCQUFrQixDQUFDO0FBQ2pELE9BQU8sRUFBRSxZQUFZLEVBQUUsTUFBTSxnQkFBZ0IsQ0FBQztBQVU5QztJQUE2QywyQ0FBYTtJQUt4RDtRQUFBLFlBQ0UsaUJBQU8sU0FDUjtRQUxELGtCQUFZLEdBQVksS0FBSyxDQUFDO1FBQzlCLGtCQUFZLEdBQUcsSUFBSSxXQUFXLEVBQUUsQ0FBQzs7SUFJakMsQ0FBQztJQUVELDBDQUFRLEdBQVI7UUFBQSxpQkFVQztRQVRDLElBQUksQ0FBQyxtQkFBbUIsR0FBRyxJQUFJLENBQUMsWUFBWSxDQUFDLFlBQVk7YUFDdEQsSUFBSSxDQUFDLFlBQVksQ0FBQyxJQUFJLENBQUMsS0FBSyxDQUFDLENBQUM7YUFDOUIsU0FBUyxDQUFDLFVBQUMsT0FBZ0I7WUFDMUIsS0FBSSxDQUFDLFlBQVksR0FBRyxJQUFJLENBQUM7WUFDekIsSUFBTSxPQUFPLEdBQUcsQ0FBQyxLQUFJLENBQUMsTUFBTSxDQUFDLGVBQWUsRUFBRSxJQUFJLEtBQUksQ0FBQyxNQUFNLENBQUMsZUFBZSxFQUFFLENBQUMsSUFBSSxDQUFDLElBQUksSUFBSSxDQUFDO1lBQzlGLElBQU0sUUFBUSxHQUFHLENBQUMsS0FBSSxDQUFDLE1BQU0sQ0FBQyxlQUFlLEVBQUUsSUFBSSxLQUFJLENBQUMsTUFBTSxDQUFDLGVBQWUsRUFBRSxDQUFDLEtBQUssQ0FBQyxJQUFJLEtBQUssQ0FBQztZQUNqRyxLQUFJLENBQUMsS0FBSyxHQUFHLE9BQU8sQ0FBQyxDQUFDLENBQUMsT0FBTyxDQUFDLENBQUMsQ0FBQyxRQUFRLENBQUM7WUFDMUMsS0FBSSxDQUFDLFNBQVMsRUFBRSxDQUFDO1FBQ25CLENBQUMsQ0FBQyxDQUFDO0lBQ1AsQ0FBQztJQUVELDZDQUFXLEdBQVgsVUFBWSxLQUFVO1FBQ3BCLEtBQUssQ0FBQyxjQUFjLEVBQUUsQ0FBQztRQUN2QixJQUFJLENBQUMsS0FBSyxHQUFHLEVBQUUsQ0FBQztRQUNoQixJQUFJLENBQUMsWUFBWSxDQUFDLFFBQVEsQ0FBQyxLQUFLLEVBQUUsRUFBRSxTQUFTLEVBQUUsS0FBSyxFQUFFLENBQUMsQ0FBQztRQUN4RCxJQUFJLENBQUMsWUFBWSxHQUFHLEtBQUssQ0FBQztRQUMxQixJQUFJLENBQUMsU0FBUyxFQUFFLENBQUM7SUFDbkIsQ0FBQztJQTNCVSx1QkFBdUI7UUFSbkMsU0FBUyxDQUFDO1lBQ1QsUUFBUSxFQUFFLGlCQUFpQjtZQUMzQixRQUFRLEVBQUUsc1FBSVQ7U0FDRixDQUFDOztPQUNXLHVCQUF1QixDQTRCbkM7SUFBRCw4QkFBQztDQUFBLEFBNUJELENBQTZDLGFBQWEsR0E0QnpEO1NBNUJZLHVCQUF1QiIsInNvdXJjZXNDb250ZW50IjpbImltcG9ydCB7IENvbXBvbmVudCwgT25Jbml0IH0gZnJvbSAnQGFuZ3VsYXIvY29yZSc7XG5pbXBvcnQgeyBGb3JtQ29udHJvbCB9IGZyb20gJ0Bhbmd1bGFyL2Zvcm1zJztcblxuaW1wb3J0IHsgRGVmYXVsdEZpbHRlciB9IGZyb20gJy4vZGVmYXVsdC1maWx0ZXInO1xuaW1wb3J0IHsgZGVib3VuY2VUaW1lIH0gZnJvbSAncnhqcy9vcGVyYXRvcnMnO1xuXG5AQ29tcG9uZW50KHtcbiAgc2VsZWN0b3I6ICdjaGVja2JveC1maWx0ZXInLFxuICB0ZW1wbGF0ZTogYFxuICAgIDxpbnB1dCB0eXBlPVwiY2hlY2tib3hcIiBbZm9ybUNvbnRyb2xdPVwiaW5wdXRDb250cm9sXCIgW25nQ2xhc3NdPVwiaW5wdXRDbGFzc1wiIGNsYXNzPVwiZm9ybS1jb250cm9sXCI+XG4gICAgPGEgaHJlZj1cIiNcIiAqbmdJZj1cImZpbHRlckFjdGl2ZVwiXG4gICAgICAgICAgICAgICAgKGNsaWNrKT1cInJlc2V0RmlsdGVyKCRldmVudClcIj57e2NvbHVtbi5nZXRGaWx0ZXJDb25maWcoKT8ucmVzZXRUZXh0IHx8ICdyZXNldCd9fTwvYT5cbiAgYCxcbn0pXG5leHBvcnQgY2xhc3MgQ2hlY2tib3hGaWx0ZXJDb21wb25lbnQgZXh0ZW5kcyBEZWZhdWx0RmlsdGVyIGltcGxlbWVudHMgT25Jbml0IHtcblxuICBmaWx0ZXJBY3RpdmU6IGJvb2xlYW4gPSBmYWxzZTtcbiAgaW5wdXRDb250cm9sID0gbmV3IEZvcm1Db250cm9sKCk7XG5cbiAgY29uc3RydWN0b3IoKSB7XG4gICAgc3VwZXIoKTtcbiAgfVxuXG4gIG5nT25Jbml0KCkge1xuICAgIHRoaXMuY2hhbmdlc1N1YnNjcmlwdGlvbiA9IHRoaXMuaW5wdXRDb250cm9sLnZhbHVlQ2hhbmdlc1xuICAgICAgLnBpcGUoZGVib3VuY2VUaW1lKHRoaXMuZGVsYXkpKVxuICAgICAgLnN1YnNjcmliZSgoY2hlY2tlZDogYm9vbGVhbikgPT4ge1xuICAgICAgICB0aGlzLmZpbHRlckFjdGl2ZSA9IHRydWU7XG4gICAgICAgIGNvbnN0IHRydWVWYWwgPSAodGhpcy5jb2x1bW4uZ2V0RmlsdGVyQ29uZmlnKCkgJiYgdGhpcy5jb2x1bW4uZ2V0RmlsdGVyQ29uZmlnKCkudHJ1ZSkgfHwgdHJ1ZTtcbiAgICAgICAgY29uc3QgZmFsc2VWYWwgPSAodGhpcy5jb2x1bW4uZ2V0RmlsdGVyQ29uZmlnKCkgJiYgdGhpcy5jb2x1bW4uZ2V0RmlsdGVyQ29uZmlnKCkuZmFsc2UpIHx8IGZhbHNlO1xuICAgICAgICB0aGlzLnF1ZXJ5ID0gY2hlY2tlZCA/IHRydWVWYWwgOiBmYWxzZVZhbDtcbiAgICAgICAgdGhpcy5zZXRGaWx0ZXIoKTtcbiAgICAgIH0pO1xuICB9XG5cbiAgcmVzZXRGaWx0ZXIoZXZlbnQ6IGFueSkge1xuICAgIGV2ZW50LnByZXZlbnREZWZhdWx0KCk7XG4gICAgdGhpcy5xdWVyeSA9ICcnO1xuICAgIHRoaXMuaW5wdXRDb250cm9sLnNldFZhbHVlKGZhbHNlLCB7IGVtaXRFdmVudDogZmFsc2UgfSk7XG4gICAgdGhpcy5maWx0ZXJBY3RpdmUgPSBmYWxzZTtcbiAgICB0aGlzLnNldEZpbHRlcigpO1xuICB9XG59XG4iXX0=