import { XRoutes, XRouterModule } from '@angular/router';,
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RecipesComponent } from './recipes/recipes.component';
import { AuthGuard } from './auth/auth-guard.service';
import { SigninComponent } from './auth/signin/signin.component';
import { ShoppingListComponent } from './shopping-list/shopping-list.component';
import { RecipeDetailComponent } from './recipes/recipe-detail/recipe-detail.component';
import { RecipeEditComponent } from './recipes/recipe-edit/recipe-edit.component';
import { SignupComponent } from './auth/signup/signup.component';
import { RecipeStartComponent } from './recipes/recipe-start/recipe-start.component';
import { SidebardivComponent } from './symbols/sidebardiv/sidebardiv.component';
import { RedoAnArticleComponent } from './pages/articles/redo-an-article/redo-an-article.component';
import { HomeComponent } from './pages/home/home.component';
import { ReWrtieAnArticleComponent } from './pages/articles/re-wrtie-an-article/re-wrtie-an-article.component';
import { ProfileComponent } from './pages/users/profile/profile.component';
import { AnotherPostAnArticleComponent } from './pages/articles/another-post-an-article/another-post-an-article.component';
import { LoginComponent } from './pages/users/login/login.component';
import { SubmitAnArticleComponent } from './pages/articles/submit-an-article/submit-an-article.component';
import { ResendAgainAnArticleIiComponent } from './pages/articles/resend-again-an-article-ii/resend-again-an-article-ii.component';
import { DoYetAnotherArticleComponent } from './pages/articles/do-yet-another-article/do-yet-another-article.component';
import { RemakeAnArticleComponent } from './pages/articles/remake-an-article/remake-an-article.component';
import { ReviewAnArticleAgainComponent } from './pages/articles/review-an-article-again/review-an-article-again.component';
import { AndPostYetAnotherArticleComponent } from './pages/articles/and-post-yet-another-article/and-post-yet-another-article.component';
import { PostAnArticleComponent } from './pages/articles/post-an-article/post-an-article.component';
import { RedoAgainAnArticleComponent } from './pages/articles/redo-again-an-article/redo-again-an-article.component';
import { RewriteAnArticleComponent } from './pages/articles/rewrite-an-article/rewrite-an-article.component';
import { ResendAgainAnArticleComponent } from './pages/articles/resend-again-an-article/resend-again-an-article.component';
import { ResendAnArticleComponent } from './pages/articles/resend-an-article/resend-an-article.component';
import { AndResendAnArticleComponent } from './pages/articles/and-resend-an-article/and-resend-an-article.component';
import { PostYetAnotherArticleComponent } from './pages/articles/post-yet-another-article/post-yet-another-article.component';
import { PostAnArticleAgainComponent } from './pages/articles/post-an-article-again/post-an-article-again.component';
import { TopMenuDivComponent } from './symbols/top-menu-div/top-menu-div.component';
import { AppComponent } from './app.component';
import { RegisterComponent } from './pages/users/register/register.component';
import { Remake2AnArticleComponent } from './pages/articles/remake2-an-article/remake2-an-article.component';

const appRoutes: Routes = [
	  { path: '', redirectTo: '/recipes', pathMatch: 'full' },
  { path: 'recipes', component: RecipesComponent, children: [
    { path: '', component: RecipeStartComponent },
    { path: 'new', component: RecipeEditComponent, canActivate: [AuthGuard] },
    { path: ':id', component: RecipeDetailComponent },
    { path: ':id/edit', component: RecipeEditComponent, canActivate: [AuthGuard] },
  ] },
  { path: 'shopping-list', component: ShoppingListComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'signin', component: SigninComponent },
	{ path: 'articles_redo_an_article', component: RedoAnArticleComponent  },
	{ path: '', component: HomeComponent  },
	{ path: 'articles_re_wrtie_an_article', component: ReWrtieAnArticleComponent  },
	{ path: 'users_profile', component: ProfileComponent  },
	{ path: 'articles_another_post_an_article', component: AnotherPostAnArticleComponent  },
	{ path: 'users_login', component: LoginComponent  },
	{ path: 'articles_submit_an_article', component: SubmitAnArticleComponent  },
	{ path: 'articles_resend_again_an_article_ii', component: ResendAgainAnArticleIiComponent  },
	{ path: 'articles_do_yet_another_article', component: DoYetAnotherArticleComponent  },
	{ path: 'articles_remake_an_article', component: RemakeAnArticleComponent  },
	{ path: 'articles_review_an_article_again', component: ReviewAnArticleAgainComponent  },
	{ path: 'articles_and_post_yet_another_article', component: AndPostYetAnotherArticleComponent  },
	{ path: 'articles_post_an_article', component: PostAnArticleComponent  },
	{ path: 'articles_redo_again_an_article', component: RedoAgainAnArticleComponent  },
	{ path: 'articles_rewrite_an_article', component: RewriteAnArticleComponent  },
	{ path: 'articles_resend_again_an_article', component: ResendAgainAnArticleComponent  },
	{ path: 'articles_resend_an_article', component: ResendAnArticleComponent  },
	{ path: 'articles_and_resend_an_article', component: AndResendAnArticleComponent  },
	{ path: 'articles_post_yet_another_article', component: PostYetAnotherArticleComponent  },
	{ path: 'articles_post_an_article_again', component: PostAnArticleAgainComponent  },
	{ path: 'users_register', component: RegisterComponent  },
	{ path: 'articles_remake2_an_article', component: Remake2AnArticleComponent  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes),
    XRouterModule.forRoot(appRoutes),
  ],
  exports: [
    Output,
    RouterModule,
    Sample,
  ],
})
export class AppRoutingModule { }
