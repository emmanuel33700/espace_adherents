import { __decorate, __metadata } from "tslib";
import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Grid } from '../../lib/grid';
import { Cell } from '../../lib/data-set/cell';
import { Row } from '../../lib/data-set/row';
var CellComponent = /** @class */ (function () {
    function CellComponent() {
        this.inputClass = '';
        this.mode = 'inline';
        this.isInEditing = false;
        this.edited = new EventEmitter();
    }
    CellComponent.prototype.onEdited = function (event) {
        if (this.isNew) {
            this.grid.create(this.grid.getNewRow(), this.createConfirm);
        }
        else {
            this.grid.save(this.row, this.editConfirm);
        }
    };
    __decorate([
        Input(),
        __metadata("design:type", Grid)
    ], CellComponent.prototype, "grid", void 0);
    __decorate([
        Input(),
        __metadata("design:type", Row)
    ], CellComponent.prototype, "row", void 0);
    __decorate([
        Input(),
        __metadata("design:type", EventEmitter)
    ], CellComponent.prototype, "editConfirm", void 0);
    __decorate([
        Input(),
        __metadata("design:type", EventEmitter)
    ], CellComponent.prototype, "createConfirm", void 0);
    __decorate([
        Input(),
        __metadata("design:type", Boolean)
    ], CellComponent.prototype, "isNew", void 0);
    __decorate([
        Input(),
        __metadata("design:type", Cell)
    ], CellComponent.prototype, "cell", void 0);
    __decorate([
        Input(),
        __metadata("design:type", String)
    ], CellComponent.prototype, "inputClass", void 0);
    __decorate([
        Input(),
        __metadata("design:type", String)
    ], CellComponent.prototype, "mode", void 0);
    __decorate([
        Input(),
        __metadata("design:type", Boolean)
    ], CellComponent.prototype, "isInEditing", void 0);
    __decorate([
        Output(),
        __metadata("design:type", Object)
    ], CellComponent.prototype, "edited", void 0);
    CellComponent = __decorate([
        Component({
            selector: 'ng2-smart-table-cell',
            template: "\n    <table-cell-view-mode *ngIf=\"!isInEditing\" [cell]=\"cell\"></table-cell-view-mode>\n    <table-cell-edit-mode *ngIf=\"isInEditing\" [cell]=\"cell\"\n                          [inputClass]=\"inputClass\"\n                          (edited)=\"onEdited($event)\">\n    </table-cell-edit-mode>\n  "
        })
    ], CellComponent);
    return CellComponent;
}());
export { CellComponent };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiY2VsbC5jb21wb25lbnQuanMiLCJzb3VyY2VSb290Ijoibmc6Ly9uZzItc21hcnQtdGFibGUvIiwic291cmNlcyI6WyJsaWIvY29tcG9uZW50cy9jZWxsL2NlbGwuY29tcG9uZW50LnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxPQUFPLEVBQUUsU0FBUyxFQUFFLEtBQUssRUFBRSxNQUFNLEVBQUUsWUFBWSxFQUFFLE1BQU0sZUFBZSxDQUFDO0FBRXZFLE9BQU8sRUFBRSxJQUFJLEVBQUUsTUFBTSxnQkFBZ0IsQ0FBQztBQUN0QyxPQUFPLEVBQUUsSUFBSSxFQUFFLE1BQU0seUJBQXlCLENBQUM7QUFDL0MsT0FBTyxFQUFFLEdBQUcsRUFBRSxNQUFNLHdCQUF3QixDQUFDO0FBWTdDO0lBQUE7UUFRVyxlQUFVLEdBQVcsRUFBRSxDQUFDO1FBQ3hCLFNBQUksR0FBVyxRQUFRLENBQUM7UUFDeEIsZ0JBQVcsR0FBWSxLQUFLLENBQUM7UUFFNUIsV0FBTSxHQUFHLElBQUksWUFBWSxFQUFPLENBQUM7SUFTN0MsQ0FBQztJQVBDLGdDQUFRLEdBQVIsVUFBUyxLQUFVO1FBQ2pCLElBQUksSUFBSSxDQUFDLEtBQUssRUFBRTtZQUNkLElBQUksQ0FBQyxJQUFJLENBQUMsTUFBTSxDQUFDLElBQUksQ0FBQyxJQUFJLENBQUMsU0FBUyxFQUFFLEVBQUUsSUFBSSxDQUFDLGFBQWEsQ0FBQyxDQUFDO1NBQzdEO2FBQU07WUFDTCxJQUFJLENBQUMsSUFBSSxDQUFDLElBQUksQ0FBQyxJQUFJLENBQUMsR0FBRyxFQUFFLElBQUksQ0FBQyxXQUFXLENBQUMsQ0FBQztTQUM1QztJQUNILENBQUM7SUFsQlE7UUFBUixLQUFLLEVBQUU7a0NBQU8sSUFBSTsrQ0FBQztJQUNYO1FBQVIsS0FBSyxFQUFFO2tDQUFNLEdBQUc7OENBQUM7SUFDVDtRQUFSLEtBQUssRUFBRTtrQ0FBYyxZQUFZO3NEQUFNO0lBQy9CO1FBQVIsS0FBSyxFQUFFO2tDQUFnQixZQUFZO3dEQUFNO0lBQ2pDO1FBQVIsS0FBSyxFQUFFOztnREFBZ0I7SUFDZjtRQUFSLEtBQUssRUFBRTtrQ0FBTyxJQUFJOytDQUFDO0lBQ1g7UUFBUixLQUFLLEVBQUU7O3FEQUF5QjtJQUN4QjtRQUFSLEtBQUssRUFBRTs7K0NBQXlCO0lBQ3hCO1FBQVIsS0FBSyxFQUFFOztzREFBOEI7SUFFNUI7UUFBVCxNQUFNLEVBQUU7O2lEQUFrQztJQVpoQyxhQUFhO1FBVnpCLFNBQVMsQ0FBQztZQUNULFFBQVEsRUFBRSxzQkFBc0I7WUFDaEMsUUFBUSxFQUFFLCtTQU1UO1NBQ0YsQ0FBQztPQUNXLGFBQWEsQ0FxQnpCO0lBQUQsb0JBQUM7Q0FBQSxBQXJCRCxJQXFCQztTQXJCWSxhQUFhIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgQ29tcG9uZW50LCBJbnB1dCwgT3V0cHV0LCBFdmVudEVtaXR0ZXIgfSBmcm9tICdAYW5ndWxhci9jb3JlJztcblxuaW1wb3J0IHsgR3JpZCB9IGZyb20gJy4uLy4uL2xpYi9ncmlkJztcbmltcG9ydCB7IENlbGwgfSBmcm9tICcuLi8uLi9saWIvZGF0YS1zZXQvY2VsbCc7XG5pbXBvcnQgeyBSb3cgfSBmcm9tICcuLi8uLi9saWIvZGF0YS1zZXQvcm93JztcblxuQENvbXBvbmVudCh7XG4gIHNlbGVjdG9yOiAnbmcyLXNtYXJ0LXRhYmxlLWNlbGwnLFxuICB0ZW1wbGF0ZTogYFxuICAgIDx0YWJsZS1jZWxsLXZpZXctbW9kZSAqbmdJZj1cIiFpc0luRWRpdGluZ1wiIFtjZWxsXT1cImNlbGxcIj48L3RhYmxlLWNlbGwtdmlldy1tb2RlPlxuICAgIDx0YWJsZS1jZWxsLWVkaXQtbW9kZSAqbmdJZj1cImlzSW5FZGl0aW5nXCIgW2NlbGxdPVwiY2VsbFwiXG4gICAgICAgICAgICAgICAgICAgICAgICAgIFtpbnB1dENsYXNzXT1cImlucHV0Q2xhc3NcIlxuICAgICAgICAgICAgICAgICAgICAgICAgICAoZWRpdGVkKT1cIm9uRWRpdGVkKCRldmVudClcIj5cbiAgICA8L3RhYmxlLWNlbGwtZWRpdC1tb2RlPlxuICBgLFxufSlcbmV4cG9ydCBjbGFzcyBDZWxsQ29tcG9uZW50IHtcblxuICBASW5wdXQoKSBncmlkOiBHcmlkO1xuICBASW5wdXQoKSByb3c6IFJvdztcbiAgQElucHV0KCkgZWRpdENvbmZpcm06IEV2ZW50RW1pdHRlcjxhbnk+O1xuICBASW5wdXQoKSBjcmVhdGVDb25maXJtOiBFdmVudEVtaXR0ZXI8YW55PjtcbiAgQElucHV0KCkgaXNOZXc6IGJvb2xlYW47XG4gIEBJbnB1dCgpIGNlbGw6IENlbGw7XG4gIEBJbnB1dCgpIGlucHV0Q2xhc3M6IHN0cmluZyA9ICcnO1xuICBASW5wdXQoKSBtb2RlOiBzdHJpbmcgPSAnaW5saW5lJztcbiAgQElucHV0KCkgaXNJbkVkaXRpbmc6IGJvb2xlYW4gPSBmYWxzZTtcblxuICBAT3V0cHV0KCkgZWRpdGVkID0gbmV3IEV2ZW50RW1pdHRlcjxhbnk+KCk7XG5cbiAgb25FZGl0ZWQoZXZlbnQ6IGFueSkge1xuICAgIGlmICh0aGlzLmlzTmV3KSB7XG4gICAgICB0aGlzLmdyaWQuY3JlYXRlKHRoaXMuZ3JpZC5nZXROZXdSb3coKSwgdGhpcy5jcmVhdGVDb25maXJtKTtcbiAgICB9IGVsc2Uge1xuICAgICAgdGhpcy5ncmlkLnNhdmUodGhpcy5yb3csIHRoaXMuZWRpdENvbmZpcm0pO1xuICAgIH1cbiAgfVxufVxuIl19