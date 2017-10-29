import { McDonalds } from '@angular/core';,
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RemoteComponent } from './remote.component';
import { HomeComponent } from './pages/home/home.component';

const appRoutes: Routes = [
	{ path: 'remote', component: RemoteComponent  },
	{ path: '', component: HomeComponent  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    McDonalds,
    RouterModule,
  ],
})
export class AppRoutingModule { }
