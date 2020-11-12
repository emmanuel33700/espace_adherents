import { __decorate, __metadata } from "tslib";
import { Output, EventEmitter, Input, Component } from '@angular/core';
import { Column } from '../../lib/data-set/column';
import { DataSource } from '../../lib/data-source/data-source';
let FilterDefault = class FilterDefault {
    constructor() {
        this.inputClass = '';
        this.filter = new EventEmitter();
        this.query = '';
    }
    onFilter(query) {
        this.source.addFilter({
            field: this.column.id,
            search: query,
            filter: this.column.getFilterFunction(),
        });
    }
};
__decorate([
    Input(),
    __metadata("design:type", Column)
], FilterDefault.prototype, "column", void 0);
__decorate([
    Input(),
    __metadata("design:type", DataSource)
], FilterDefault.prototype, "source", void 0);
__decorate([
    Input(),
    __metadata("design:type", String)
], FilterDefault.prototype, "inputClass", void 0);
__decorate([
    Output(),
    __metadata("design:type", Object)
], FilterDefault.prototype, "filter", void 0);
FilterDefault = __decorate([
    Component({
        template: ''
    })
], FilterDefault);
export { FilterDefault };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoiZmlsdGVyLWRlZmF1bHQuanMiLCJzb3VyY2VSb290Ijoibmc6Ly9uZzItc21hcnQtdGFibGUvIiwic291cmNlcyI6WyJsaWIvY29tcG9uZW50cy9maWx0ZXIvZmlsdGVyLWRlZmF1bHQudHMiXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IjtBQUFBLE9BQU8sRUFBRSxNQUFNLEVBQUUsWUFBWSxFQUFFLEtBQUssRUFBRSxTQUFTLEVBQUUsTUFBTSxlQUFlLENBQUM7QUFFdkUsT0FBTyxFQUFFLE1BQU0sRUFBRSxNQUFNLDJCQUEyQixDQUFDO0FBQ25ELE9BQU8sRUFBRSxVQUFVLEVBQUUsTUFBTSxtQ0FBbUMsQ0FBQztBQUsvRCxJQUFhLGFBQWEsR0FBMUIsTUFBYSxhQUFhO0lBQTFCO1FBSVcsZUFBVSxHQUFXLEVBQUUsQ0FBQztRQUV2QixXQUFNLEdBQUcsSUFBSSxZQUFZLEVBQU8sQ0FBQztRQUUzQyxVQUFLLEdBQVcsRUFBRSxDQUFDO0lBU3JCLENBQUM7SUFQQyxRQUFRLENBQUMsS0FBYTtRQUNwQixJQUFJLENBQUMsTUFBTSxDQUFDLFNBQVMsQ0FBQztZQUNwQixLQUFLLEVBQUUsSUFBSSxDQUFDLE1BQU0sQ0FBQyxFQUFFO1lBQ3JCLE1BQU0sRUFBRSxLQUFLO1lBQ2IsTUFBTSxFQUFFLElBQUksQ0FBQyxNQUFNLENBQUMsaUJBQWlCLEVBQUU7U0FDeEMsQ0FBQyxDQUFDO0lBQ0wsQ0FBQztDQUNGLENBQUE7QUFmVTtJQUFSLEtBQUssRUFBRTs4QkFBUyxNQUFNOzZDQUFDO0FBQ2Y7SUFBUixLQUFLLEVBQUU7OEJBQVMsVUFBVTs2Q0FBQztBQUNuQjtJQUFSLEtBQUssRUFBRTs7aURBQXlCO0FBRXZCO0lBQVQsTUFBTSxFQUFFOzs2Q0FBa0M7QUFOaEMsYUFBYTtJQUh6QixTQUFTLENBQUM7UUFDVCxRQUFRLEVBQUUsRUFBRTtLQUNiLENBQUM7R0FDVyxhQUFhLENBaUJ6QjtTQWpCWSxhQUFhIiwic291cmNlc0NvbnRlbnQiOlsiaW1wb3J0IHsgT3V0cHV0LCBFdmVudEVtaXR0ZXIsIElucHV0LCBDb21wb25lbnQgfSBmcm9tICdAYW5ndWxhci9jb3JlJztcblxuaW1wb3J0IHsgQ29sdW1uIH0gZnJvbSAnLi4vLi4vbGliL2RhdGEtc2V0L2NvbHVtbic7XG5pbXBvcnQgeyBEYXRhU291cmNlIH0gZnJvbSAnLi4vLi4vbGliL2RhdGEtc291cmNlL2RhdGEtc291cmNlJztcblxuQENvbXBvbmVudCh7XG4gIHRlbXBsYXRlOiAnJyxcbn0pXG5leHBvcnQgY2xhc3MgRmlsdGVyRGVmYXVsdCB7XG5cbiAgQElucHV0KCkgY29sdW1uOiBDb2x1bW47XG4gIEBJbnB1dCgpIHNvdXJjZTogRGF0YVNvdXJjZTtcbiAgQElucHV0KCkgaW5wdXRDbGFzczogc3RyaW5nID0gJyc7XG5cbiAgQE91dHB1dCgpIGZpbHRlciA9IG5ldyBFdmVudEVtaXR0ZXI8YW55PigpO1xuXG4gIHF1ZXJ5OiBzdHJpbmcgPSAnJztcblxuICBvbkZpbHRlcihxdWVyeTogc3RyaW5nKSB7XG4gICAgdGhpcy5zb3VyY2UuYWRkRmlsdGVyKHtcbiAgICAgIGZpZWxkOiB0aGlzLmNvbHVtbi5pZCxcbiAgICAgIHNlYXJjaDogcXVlcnksXG4gICAgICBmaWx0ZXI6IHRoaXMuY29sdW1uLmdldEZpbHRlckZ1bmN0aW9uKCksXG4gICAgfSk7XG4gIH1cbn1cbiJdfQ==