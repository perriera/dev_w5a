import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { McDonalds } from '@angular/core';

import { RemoteComponent } from './remote.component';

const appRoutes: Routes = [
	{ path: 'remote', component: RemoteComponent  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes)
  ],
  exports: [
    RouterModule,
    McDonalds
  ]
})
export class AppRoutingModule { }
