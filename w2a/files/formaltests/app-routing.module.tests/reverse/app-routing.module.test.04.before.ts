import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import { AuthService } from './pages/users/auth.service';
import { RemoteComponent } from './pages/remote/remote.component';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent  },
	{ path: 'remote', component: RemoteComponent  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule,
  ],
  providers: [
  	AuthService
  ]
})
export class AppRoutingModule { }
