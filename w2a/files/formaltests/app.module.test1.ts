import { APP_BASE_HREF } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AndPostYetAnotherArticleComponent } from './pages/articles/and-post-yet-another-article/and-post-yet-another-article.component';
import { AndResendAnArticleComponent } from './pages/articles/and-resend-an-article/and-resend-an-article.component';
import { AnotherPostAnArticleComponent } from './pages/articles/another-post-an-article/another-post-an-article.component';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthService } from './pages/users/auth.service';
import { DoYetAnotherArticleComponent } from './pages/articles/do-yet-another-article/do-yet-another-article.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/users/login/login.component';
import { PostAnArticleAgainComponent } from './pages/articles/post-an-article-again/post-an-article-again.component';
import { PostAnArticleComponent } from './pages/articles/post-an-article/post-an-article.component';
import { PostYetAnotherArticleComponent } from './pages/articles/post-yet-another-article/post-yet-another-article.component';
import { ProfileComponent } from './pages/users/profile/profile.component';
import { ReWrtieAnArticleComponent } from './pages/articles/re-wrtie-an-article/re-wrtie-an-article.component';
import { RedoAgainAnArticleComponent } from './pages/articles/redo-again-an-article/redo-again-an-article.component';
import { RedoAnArticleComponent } from './pages/articles/redo-an-article/redo-an-article.component';
import { RegisterComponent } from './pages/users/register/register.component';
import { Remake2AnArticleComponent } from './pages/articles/remake2-an-article/remake2-an-article.component';
import { RemakeAnArticleComponent } from './pages/articles/remake-an-article/remake-an-article.component';
import { ResendAgainAnArticleComponent } from './pages/articles/resend-again-an-article/resend-again-an-article.component';
import { ResendAgainAnArticleIiComponent } from './pages/articles/resend-again-an-article-ii/resend-again-an-article-ii.component';
import { ResendAnArticleComponent } from './pages/articles/resend-an-article/resend-an-article.component';
import { ReviewAnArticleAgainComponent } from './pages/articles/review-an-article-again/review-an-article-again.component';
import { RewriteAnArticleComponent } from './pages/articles/rewrite-an-article/rewrite-an-article.component';
import { ServerService } from './server.service';
import { SidebardivComponent } from './symbols/sidebardiv/sidebardiv.component';
import { SubmitAnArticleComponent } from './pages/articles/submit-an-article/submit-an-article.component';
import { TopMenuDivComponent } from './symbols/top-menu-div/top-menu-div.component';

@NgModule({
  declarations: [
    AndPostYetAnotherArticleComponent,
    AndResendAnArticleComponent,
    AnotherPostAnArticleComponent,
    AppComponent,
    DoYetAnotherArticleComponent,
    HomeComponent,
    LoginComponent,
    PostAnArticleAgainComponent,
    PostAnArticleComponent,
    PostYetAnotherArticleComponent,
    ProfileComponent,
    ReWrtieAnArticleComponent,
    RedoAgainAnArticleComponent,
    RedoAnArticleComponent,
    RegisterComponent,
    Remake2AnArticleComponent,
    RemakeAnArticleComponent,
    ResendAgainAnArticleComponent,
    ResendAgainAnArticleIiComponent,
    ResendAnArticleComponent,
    ReviewAnArticleAgainComponent,
    RewriteAnArticleComponent,
    SidebardivComponent,
    SubmitAnArticleComponent,
    TopMenuDivComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule,
  ],
  providers: [
    AuthService,
    ServerService,
    {'useValue':'/','provide':'APP_BASE_HREF'},
  ],
  bootstrap: [
    AppComponent,
  ],
})
export class AppModule { }

