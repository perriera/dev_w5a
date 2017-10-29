import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';
import { APP_BASE_HREF } from '@angular/common';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { SidebardivComponent } from './symbols/sidebardiv/sidebardiv.component';
import { TopMenuDivComponent } from './symbols/top-menu-div/top-menu-div.component';
import { RegisterComponent } from './pages/users/register/register.component';
import { LoginComponent } from './pages/users/login/login.component';
import { ServerService } from './server.service';
import { AuthService } from './pages/users/auth.service';
import { SubmitAnArticleComponent } from './pages/articles/submit-an-article/submit-an-article.component';
import { ProfileComponent } from './pages/users/profile/profile.component';
import { ResendAnArticleComponent } from './pages/articles/resend-an-article/resend-an-article.component';
import { ResendAgainAnArticleComponent } from './pages/articles/resend-again-an-article/resend-again-an-article.component';
import { RewriteAnArticleComponent } from './pages/articles/rewrite-an-article/rewrite-an-article.component';
import { ReWrtieAnArticleComponent } from './pages/articles/re-wrtie-an-article/re-wrtie-an-article.component';

const appRoutes: Routes = [
	{ path: '', component:  HomeComponent  },
	{ path: 'users_profile', component:  ProfileComponent  },
	{ path: 'users_login', component:  LoginComponent  },
	{ path: 'articles_submit_an_article', component:  SubmitAnArticleComponent  },
	{ path: 'articles_resend_an_article', component:  ResendAnArticleComponent  },
	{ path: 'users_auth.service', component:  AuthService  },
	{ path: 'users_register', component:  RegisterComponent  },
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SidebardivComponent,
    RegisterComponent,
    LoginComponent,
    TopMenuDivComponent,
    TopMenuDivComponent,
    RegisterComponent,
    LoginComponent,
    SubmitAnArticleComponent,
    ProfileComponent,
    ResendAnArticleComponent,
    ResendAgainAnArticleComponent,
    RewriteAnArticleComponent,
    ReWrtieAnArticleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [ { provide: APP_BASE_HREF, useValue: '/' }, ServerService, AuthService ],
  bootstrap: [ AppComponent ]
})
export class AppModule {
}

