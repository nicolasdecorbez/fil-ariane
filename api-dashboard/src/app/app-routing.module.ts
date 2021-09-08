import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DatabaseComponent } from './database/database.component';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { MainComponent } from './main/main.component';
import { ServerComponent } from './server/server.component';
import { AuthGuard } from './services/auth-guard.service';

const routes: Routes = [
  { 
    path: 'database',
    canActivate: [AuthGuard],
    component: DatabaseComponent 
  },
  {
    path: 'server',
    canActivate: [AuthGuard],
    component: ServerComponent
  },
  { 
    path: '',
    component: MainComponent 
  },
  { 
    path: 'not-found', 
    component: FourOhFourComponent 
  },
  { 
    path: '**', 
    redirectTo: 'not-found' 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
