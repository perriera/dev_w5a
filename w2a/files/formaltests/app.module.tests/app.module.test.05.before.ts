import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ArticleResultsComponent } from './symbols/article-results/article-results.component';
import { ArticleService } from './controls/article.service';
import { AuthGuard } from './controls/auth-guard.service';
import { AuthService } from './controls/auth.service';
import { HomeComponent } from './views/home/home.component';
import { LoginComponent } from './views/users/login/login.component';
import { PostAnArticleComponent } from './views/articles/post-an-article/post-an-article.component';
import { ProfileComponent } from './views/users/profile/profile.component';
import { QuillEditorModule } from 'ng2-quill-editor';
import { RegisterComponent } from './views/users/register/register.component';
import { SidebardivComponent } from './symbols/sidebardiv/sidebardiv.component';
import { SubmitAnArticleComponent } from './views/articles/submit-an-article/submit-an-article.component';
import { TopMenuDivComponent } from './symbols/top-menu-div/top-menu-div.component';
import { UtilitiesComponent } from './views/users/utilities/utilities.component';
import { ReviewAnArticleComponent } from './views/articles/review-an-article/review-an-article.component';

@NgModule({
  declarations: [
    AppComponent,
    ArticleResultsComponent,
    HomeComponent,
    LoginComponent,
    PostAnArticleComponent,
    ProfileComponent,
    RegisterComponent,
    SidebardivComponent,
    SubmitAnArticleComponent,
    TopMenuDivComponent,
    UtilitiesComponent,
    ReviewAnArticleComponent,
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    QuillEditorModule,
    RouterModule,
  ],
  providers: [
    ArticleService,
    AuthGuard,
    AuthService,
    {'useValue':'/','provide':'APP_BASE_HREF'},
  ],
  bootstrap: [
    AppComponent,
  ],
})
export class AppModule { }

