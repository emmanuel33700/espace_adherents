import { __decorate, __metadata } from "tslib";
import { Component, Input } from '@angular/core';
import { Grid } from '../../../lib/grid';
import { DataSource } from '../../../lib/data-source/data-source';
let CheckboxSelectAllComponent = class CheckboxSelectAllComponent {
};
__decorate([
    Input(),
    __metadata("design:type", Grid)
], CheckboxSelectAllComponent.prototype, "grid", void 0);
__decorate([
    Input(),
    __metadata("design:type", DataSource)
], CheckboxSelectAllComponent.prototype, "source", void 0);
__decorate([
    Input(),
    __metadata("design:type", Boolean)
], CheckboxSelectAllComponent.prototype, "isAllSelected", void 0);
CheckboxSelectAllComponent = __decorate([
    Component({
        selector: '[ng2-st-checkbox-select-all]',
        template: `
    <input type="checkbox" [ngModel]="isAllSelected">
  `
    })
], CheckboxSelectAllComponent);
export { CheckboxSelectAllComponent };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiY2hlY2tib3gtc2VsZWN0LWFsbC5jb21wb25lbnQuanMiLCJzb3VyY2VSb290Ijoibmc6Ly9uZzItc21hcnQtdGFibGUvIiwic291cmNlcyI6WyJsaWIvY29tcG9uZW50cy90aGVhZC9jZWxscy9jaGVja2JveC1zZWxlY3QtYWxsLmNvbXBvbmVudC50cyJdLCJuYW1lcyI6W10sIm1hcHBpbmdzIjoiO0FBQUEsT0FBTyxFQUFFLFNBQVMsRUFBRSxLQUFLLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFFakQsT0FBTyxFQUFFLElBQUksRUFBRSxNQUFNLG1CQUFtQixDQUFDO0FBQ3pDLE9BQU8sRUFBRSxVQUFVLEVBQUUsTUFBTSxzQ0FBc0MsQ0FBQztBQVFsRSxJQUFhLDBCQUEwQixHQUF2QyxNQUFhLDBCQUEwQjtDQUt0QyxDQUFBO0FBSFU7SUFBUixLQUFLLEVBQUU7OEJBQU8sSUFBSTt3REFBQztBQUNYO0lBQVIsS0FBSyxFQUFFOzhCQUFTLFVBQVU7MERBQUM7QUFDbkI7SUFBUixLQUFLLEVBQUU7O2lFQUF3QjtBQUpyQiwwQkFBMEI7SUFOdEMsU0FBUyxDQUFDO1FBQ1QsUUFBUSxFQUFFLDhCQUE4QjtRQUN4QyxRQUFRLEVBQUU7O0dBRVQ7S0FDRixDQUFDO0dBQ1csMEJBQTBCLENBS3RDO1NBTFksMEJBQTBCIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgQ29tcG9uZW50LCBJbnB1dCB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xuXG5pbXBvcnQgeyBHcmlkIH0gZnJvbSAnLi4vLi4vLi4vbGliL2dyaWQnO1xuaW1wb3J0IHsgRGF0YVNvdXJjZSB9IGZyb20gJy4uLy4uLy4uL2xpYi9kYXRhLXNvdXJjZS9kYXRhLXNvdXJjZSc7XG5cbkBDb21wb25lbnQoe1xuICBzZWxlY3RvcjogJ1tuZzItc3QtY2hlY2tib3gtc2VsZWN0LWFsbF0nLFxuICB0ZW1wbGF0ZTogYFxuICAgIDxpbnB1dCB0eXBlPVwiY2hlY2tib3hcIiBbbmdNb2RlbF09XCJpc0FsbFNlbGVjdGVkXCI+XG4gIGAsXG59KVxuZXhwb3J0IGNsYXNzIENoZWNrYm94U2VsZWN0QWxsQ29tcG9uZW50IHtcblxuICBASW5wdXQoKSBncmlkOiBHcmlkO1xuICBASW5wdXQoKSBzb3VyY2U6IERhdGFTb3VyY2U7XG4gIEBJbnB1dCgpIGlzQWxsU2VsZWN0ZWQ6IGJvb2xlYW47XG59XG4iXX0=