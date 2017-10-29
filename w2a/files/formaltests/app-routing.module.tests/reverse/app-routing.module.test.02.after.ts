import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthService } from './pages/users/auth.service';
import { HomeComponent } from './pages/home/home.component';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule,
  ],
  providers: [
    AuthService,
  ],
})
export class AppRoutingModule { }
