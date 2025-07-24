import { Routes } from '@angular/router';
import {PeopleListComponent } from './people/list/list.component';
import { PlanetListComponent } from './planets/list/list.component';
import { NotFoundComponent } from './layout/not-found/not-found.component';

export const routes: Routes = [
        {
        path: '', 
        redirectTo: 'people', 
        pathMatch: 'full', 
      },
      {
        path: 'people',
        component: PeopleListComponent,
      },
      {
        path: 'people',
        component: PeopleListComponent,
        pathMatch: 'full',
      },
      {
        path: 'planets',
        component: PlanetListComponent,
        pathMatch: 'full',
      },
      {
        path: '**',
        component: NotFoundComponent
      }
];
