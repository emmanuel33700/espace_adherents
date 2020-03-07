import { NgModule } from '@angular/core';
import { NbLayoutModule } from '@nebular/theme';



import { ThemeModule } from '../@theme/theme.module';
import { AuthComponent } from './auth.component';
import { LoginComponent } from './login/login.component';
import { AuthRoutingModule } from './auth-routing.module';
import {FormsModule as ngFormsModule} from '@angular/forms';

@NgModule({
  imports: [
    AuthRoutingModule, NbLayoutModule,
  ],
  declarations: [
    AuthComponent,
  ],
})
export class AuthModule {
}
