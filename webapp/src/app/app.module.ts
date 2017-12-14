import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';

import {MaterialModule} from './material.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AppRoutingModule} from './app-routing.module';


import {AppComponent} from './app.component';
import {ToolbarComponent} from './toolbar/toolbar.component';
import {ProgressbarComponent} from './progressbar/progressbar.component';
import {MainComponent} from './main/main.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { FooterComponent } from './footer/footer.component';


@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    ProgressbarComponent,
    MainComponent,
    NotFoundComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
