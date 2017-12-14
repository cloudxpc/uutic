import {NgModule} from '@angular/core';

import {RouterModule, Routes} from '@angular/router';

import {MainComponent} from './main/main.component';
import {NotFoundComponent} from './not-found/not-found.component';

const appRoutes: Routes = [
  {path: '', component: MainComponent},
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
