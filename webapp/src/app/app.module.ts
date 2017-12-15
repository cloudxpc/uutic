import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';

import {MaterialModule} from './material.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AppRoutingModule} from './app-routing.module';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppInterceptor} from './app-interceptor';

import {AppComponent} from './app.component';
import {ToolbarComponent} from './header/toolbar/toolbar.component';
import {ProgressbarComponent} from './header/progressbar/progressbar.component';
import {MainComponent} from './main/main.component';
import {NotFoundComponent} from './main/not-found/not-found.component';
import {FooterComponent} from './footer/footer.component';
import { ProgressbarService } from './header/progressbar/progressbar.service';
import { ToolbarService } from './header/toolbar/toolbar.service';
import { HeaderComponent } from './header/header.component';
import { WelcomeComponent } from './main/welcome/welcome.component';


@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    ProgressbarComponent,
    MainComponent,
    NotFoundComponent,
    FooterComponent,
    HeaderComponent,
    WelcomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    ProgressbarService,
    ToolbarService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AppInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
