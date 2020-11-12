import { __decorate, __metadata } from "tslib";
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Column } from '../../../lib/data-set/column';
import { DataSource } from '../../../lib/data-source/data-source';
var ColumnTitleComponent = /** @class */ (function () {
    function ColumnTitleComponent() {
        this.sort = new EventEmitter();
    }
    __decorate([
        Input(),
        __metadata("design:type", Column)
    ], ColumnTitleComponent.prototype, "column", void 0);
    __decorate([
        Input(),
        __metadata("design:type", DataSource)
    ], ColumnTitleComponent.prototype, "source", void 0);
    __decorate([
        Output(),
        __metadata("design:type", Object)
    ], ColumnTitleComponent.prototype, "sort", void 0);
    ColumnTitleComponent = __decorate([
        Component({
            selector: 'ng2-st-column-title',
            template: "\n    <div class=\"ng2-smart-title\">\n      <ng2-smart-table-title [source]=\"source\" [column]=\"column\" (sort)=\"sort.emit($event)\"></ng2-smart-table-title>\n    </div>\n  "
        })
    ], ColumnTitleComponent);
    return ColumnTitleComponent;
}());
export { ColumnTitleComponent };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiY29sdW1uLXRpdGxlLmNvbXBvbmVudC5qcyIsInNvdXJjZVJvb3QiOiJuZzovL25nMi1zbWFydC10YWJsZS8iLCJzb3VyY2VzIjpbImxpYi9jb21wb25lbnRzL3RoZWFkL2NlbGxzL2NvbHVtbi10aXRsZS5jb21wb25lbnQudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IjtBQUFBLE9BQU8sRUFBRSxTQUFTLEVBQUUsS0FBSyxFQUFFLE1BQU0sRUFBRSxZQUFZLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFFdkUsT0FBTyxFQUFFLE1BQU0sRUFBRSxNQUFNLDhCQUE4QixDQUFDO0FBQ3RELE9BQU8sRUFBRSxVQUFVLEVBQUUsTUFBTSxzQ0FBc0MsQ0FBQztBQVVsRTtJQUFBO1FBS1ksU0FBSSxHQUFHLElBQUksWUFBWSxFQUFPLENBQUM7SUFFM0MsQ0FBQztJQUxVO1FBQVIsS0FBSyxFQUFFO2tDQUFTLE1BQU07d0RBQUM7SUFDZjtRQUFSLEtBQUssRUFBRTtrQ0FBUyxVQUFVO3dEQUFDO0lBRWxCO1FBQVQsTUFBTSxFQUFFOztzREFBZ0M7SUFMOUIsb0JBQW9CO1FBUmhDLFNBQVMsQ0FBQztZQUNULFFBQVEsRUFBRSxxQkFBcUI7WUFDL0IsUUFBUSxFQUFFLG1MQUlUO1NBQ0YsQ0FBQztPQUNXLG9CQUFvQixDQU9oQztJQUFELDJCQUFDO0NBQUEsQUFQRCxJQU9DO1NBUFksb0JBQW9CIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgQ29tcG9uZW50LCBJbnB1dCwgT3V0cHV0LCBFdmVudEVtaXR0ZXIgfSBmcm9tICdAYW5ndWxhci9jb3JlJztcblxuaW1wb3J0IHsgQ29sdW1uIH0gZnJvbSAnLi4vLi4vLi4vbGliL2RhdGEtc2V0L2NvbHVtbic7XG5pbXBvcnQgeyBEYXRhU291cmNlIH0gZnJvbSAnLi4vLi4vLi4vbGliL2RhdGEtc291cmNlL2RhdGEtc291cmNlJztcblxuQENvbXBvbmVudCh7XG4gIHNlbGVjdG9yOiAnbmcyLXN0LWNvbHVtbi10aXRsZScsXG4gIHRlbXBsYXRlOiBgXG4gICAgPGRpdiBjbGFzcz1cIm5nMi1zbWFydC10aXRsZVwiPlxuICAgICAgPG5nMi1zbWFydC10YWJsZS10aXRsZSBbc291cmNlXT1cInNvdXJjZVwiIFtjb2x1bW5dPVwiY29sdW1uXCIgKHNvcnQpPVwic29ydC5lbWl0KCRldmVudClcIj48L25nMi1zbWFydC10YWJsZS10aXRsZT5cbiAgICA8L2Rpdj5cbiAgYCxcbn0pXG5leHBvcnQgY2xhc3MgQ29sdW1uVGl0bGVDb21wb25lbnQge1xuXG4gIEBJbnB1dCgpIGNvbHVtbjogQ29sdW1uO1xuICBASW5wdXQoKSBzb3VyY2U6IERhdGFTb3VyY2U7XG5cbiAgQE91dHB1dCgpIHNvcnQgPSBuZXcgRXZlbnRFbWl0dGVyPGFueT4oKTtcblxufVxuIl19