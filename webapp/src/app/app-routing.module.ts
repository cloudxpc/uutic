import {NgModule} from '@angular/core';

import {RouterModule, Routes} from '@angular/router';

import {NotFoundComponent} from './main/not-found/not-found.component';
import {WelcomeComponent} from "./main/welcome/welcome.component";

const appRoutes: Routes = [
  {path: '', component: WelcomeComponent},
  // { path: '', redirectTo: '/main', pathMatch: 'full'},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
