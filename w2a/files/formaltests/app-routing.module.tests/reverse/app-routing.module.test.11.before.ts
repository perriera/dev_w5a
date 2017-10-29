import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from './controls/auth-guard.service';
import { HomeComponent } from './views/home/home.component';
import { PostAnArticleComponent } from './views/articles/post-an-article/post-an-article.component';
import { ProfileComponent } from './views/users/profile/profile.component';
import { UtilitiesComponent } from './views/users/utilities/utilities.component';
import { LoginComponent } from './views/users/login/login.component';
import { SubmitAnArticleComponent } from './views/articles/submit-an-article/submit-an-article.component';
import { RegisterComponent } from './views/users/register/register.component';

const appRoutes: Routes = [
	{ path: '', component: HomeComponent  },
	{ path: 'users_profile', component: ProfileComponent, canActivate: [AuthGuard] },
	{ path: 'users_login', component: LoginComponent  },
	{ path: 'articles_submit_an_article', component: SubmitAnArticleComponent, canActivate: [AuthGuard] },
	{ path: 'articles_post_an_article', component: PostAnArticleComponent, canActivate: [AuthGuard] },
	{ path: 'users_register', component: RegisterComponent  },
	{ path: 'users_utilities', component: UtilitiesComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
  ],
  exports: [
    RouterModule,
  ],
})
export class AppRoutingModule { }

