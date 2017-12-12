import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';

import {MaterialModule} from './material.module';

import {AppComponent} from './app.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import { ProgressbarComponent } from './progressbar/progressbar.component';
import { MainComponent } from './main/main.component';


@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    ProgressbarComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
