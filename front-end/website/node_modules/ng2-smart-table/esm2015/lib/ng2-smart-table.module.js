import { __decorate } from "tslib";
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CellModule } from './components/cell/cell.module';
import { FilterModule } from './components/filter/filter.module';
import { PagerModule } from './components/pager/pager.module';
import { TBodyModule } from './components/tbody/tbody.module';
import { THeadModule } from './components/thead/thead.module';
import { Ng2SmartTableComponent } from './ng2-smart-table.component';
let Ng2SmartTableModule = class Ng2SmartTableModule {
};
Ng2SmartTableModule = __decorate([
    NgModule({
        imports: [
            CommonModule,
            FormsModule,
            ReactiveFormsModule,
            CellModule,
            FilterModule,
            PagerModule,
            TBodyModule,
            THeadModule,
        ],
        declarations: [
            Ng2SmartTableComponent,
        ],
        exports: [
            Ng2SmartTableComponent,
        ],
    })
], Ng2SmartTableModule);
export { Ng2SmartTableModule };
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJmaWxlIjoibmcyLXNtYXJ0LXRhYmxlLm1vZHVsZS5qcyIsInNvdXJjZVJvb3QiOiJuZzovL25nMi1zbWFydC10YWJsZS8iLCJzb3VyY2VzIjpbImxpYi9uZzItc21hcnQtdGFibGUubW9kdWxlLnRzIl0sIm5hbWVzIjpbXSwibWFwcGluZ3MiOiI7QUFBQSxPQUFPLEVBQUUsUUFBUSxFQUFFLE1BQU0sZUFBZSxDQUFDO0FBQ3pDLE9BQU8sRUFBRSxZQUFZLEVBQUUsTUFBTSxpQkFBaUIsQ0FBQztBQUMvQyxPQUFPLEVBQUUsV0FBVyxFQUFFLG1CQUFtQixFQUFFLE1BQU0sZ0JBQWdCLENBQUM7QUFFbEUsT0FBTyxFQUFFLFVBQVUsRUFBRSxNQUFNLCtCQUErQixDQUFDO0FBQzNELE9BQU8sRUFBRSxZQUFZLEVBQUUsTUFBTSxtQ0FBbUMsQ0FBQztBQUNqRSxPQUFPLEVBQUUsV0FBVyxFQUFFLE1BQU0saUNBQWlDLENBQUM7QUFDOUQsT0FBTyxFQUFFLFdBQVcsRUFBRSxNQUFNLGlDQUFpQyxDQUFDO0FBQzlELE9BQU8sRUFBRSxXQUFXLEVBQUUsTUFBTSxpQ0FBaUMsQ0FBQztBQUU5RCxPQUFPLEVBQUUsc0JBQXNCLEVBQUUsTUFBTSw2QkFBNkIsQ0FBQztBQW9CckUsSUFBYSxtQkFBbUIsR0FBaEMsTUFBYSxtQkFBbUI7Q0FDL0IsQ0FBQTtBQURZLG1CQUFtQjtJQWxCL0IsUUFBUSxDQUFDO1FBQ1IsT0FBTyxFQUFFO1lBQ1AsWUFBWTtZQUNaLFdBQVc7WUFDWCxtQkFBbUI7WUFDbkIsVUFBVTtZQUNWLFlBQVk7WUFDWixXQUFXO1lBQ1gsV0FBVztZQUNYLFdBQVc7U0FDWjtRQUNELFlBQVksRUFBRTtZQUNaLHNCQUFzQjtTQUN2QjtRQUNELE9BQU8sRUFBRTtZQUNQLHNCQUFzQjtTQUN2QjtLQUNGLENBQUM7R0FDVyxtQkFBbUIsQ0FDL0I7U0FEWSxtQkFBbUIiLCJzb3VyY2VzQ29udGVudCI6WyJpbXBvcnQgeyBOZ01vZHVsZSB9IGZyb20gJ0Bhbmd1bGFyL2NvcmUnO1xuaW1wb3J0IHsgQ29tbW9uTW9kdWxlIH0gZnJvbSAnQGFuZ3VsYXIvY29tbW9uJztcbmltcG9ydCB7IEZvcm1zTW9kdWxlLCBSZWFjdGl2ZUZvcm1zTW9kdWxlIH0gZnJvbSAnQGFuZ3VsYXIvZm9ybXMnO1xuXG5pbXBvcnQgeyBDZWxsTW9kdWxlIH0gZnJvbSAnLi9jb21wb25lbnRzL2NlbGwvY2VsbC5tb2R1bGUnO1xuaW1wb3J0IHsgRmlsdGVyTW9kdWxlIH0gZnJvbSAnLi9jb21wb25lbnRzL2ZpbHRlci9maWx0ZXIubW9kdWxlJztcbmltcG9ydCB7IFBhZ2VyTW9kdWxlIH0gZnJvbSAnLi9jb21wb25lbnRzL3BhZ2VyL3BhZ2VyLm1vZHVsZSc7XG5pbXBvcnQgeyBUQm9keU1vZHVsZSB9IGZyb20gJy4vY29tcG9uZW50cy90Ym9keS90Ym9keS5tb2R1bGUnO1xuaW1wb3J0IHsgVEhlYWRNb2R1bGUgfSBmcm9tICcuL2NvbXBvbmVudHMvdGhlYWQvdGhlYWQubW9kdWxlJztcblxuaW1wb3J0IHsgTmcyU21hcnRUYWJsZUNvbXBvbmVudCB9IGZyb20gJy4vbmcyLXNtYXJ0LXRhYmxlLmNvbXBvbmVudCc7XG5cbkBOZ01vZHVsZSh7XG4gIGltcG9ydHM6IFtcbiAgICBDb21tb25Nb2R1bGUsXG4gICAgRm9ybXNNb2R1bGUsXG4gICAgUmVhY3RpdmVGb3Jtc01vZHVsZSxcbiAgICBDZWxsTW9kdWxlLFxuICAgIEZpbHRlck1vZHVsZSxcbiAgICBQYWdlck1vZHVsZSxcbiAgICBUQm9keU1vZHVsZSxcbiAgICBUSGVhZE1vZHVsZSxcbiAgXSxcbiAgZGVjbGFyYXRpb25zOiBbXG4gICAgTmcyU21hcnRUYWJsZUNvbXBvbmVudCxcbiAgXSxcbiAgZXhwb3J0czogW1xuICAgIE5nMlNtYXJ0VGFibGVDb21wb25lbnQsXG4gIF0sXG59KVxuZXhwb3J0IGNsYXNzIE5nMlNtYXJ0VGFibGVNb2R1bGUge1xufVxuIl19