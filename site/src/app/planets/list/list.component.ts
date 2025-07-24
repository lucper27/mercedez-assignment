import { Component, inject } from '@angular/core';
import { PlanetService } from '../services/planet.service';
import { IPlanet } from '../models/planet.model';
import { FIXED_SIZE } from '../../shared/constants/pagination.constants';
import { ICommonFilter } from '../../shared/models/filter.model';
import { DataTableComponent } from '../../shared/components/data-table/data-table.component';

@Component({
  selector: 'planet-list',
  standalone: true,
  imports: [DataTableComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class PlanetListComponent {

  private readonly planetService = inject(PlanetService);

  planets?: IPlanet[];

  FIXED_SIZE = FIXED_SIZE;

  columns = [
  { field: 'name', header: 'Name' },
  { field: 'climate', header: 'Climate' },
  { field: 'terrain', header: 'Terrain' },
  { field: 'gravity', header: 'Gravity' },
  { field: 'population', header: 'Population' },
  { field: 'diameter', header: 'Diameter' },
  { field: 'rotationPeriod', header: 'Rotation Period' },
  { field: 'orbitalPeriod', header: 'Orbital Period' },
  { field: 'surfaceWater', header: 'Surface Water' },
  { field: 'created', header: 'Created' }
  ];

    filterOptions: ICommonFilter = {
      page: 1,
      name: '',
      size: this.FIXED_SIZE,
      sort: '',
      direction: ''
    }
  
    lastSortField?: string;
    lastSortDirection?: string;
    lastPage?: number;
    totalRecords: number = 0;
    loadingTable = true;
    loadingBtn = false;

   handleTableChange(event: { page: number; sort?: { field: string; order: number } }) {
    const isSamePage = this.lastPage === event.page;
    const isSameSort = event.sort?.field === this.filterOptions.sort &&
      (this.filterOptions.direction === (event.sort.order === -1 ? 'desc' : 'asc'));

    if (isSamePage && isSameSort) return;

    this.lastPage = event.page;
    this.filterOptions.page = event.page;

    if (event.sort) {
      this.filterOptions.sort = event.sort.field;
      this.filterOptions.direction = event.sort.order === -1 ? 'desc' : 'asc';
    }

    this.loadingTable = true;
    this.getPlanets();
  }
  
  onNameSearch(name: string): void {
    if (name !== '') {
      this.filterOptions.name = name;
      this.loadingTable = true;
      this.loadingBtn = true;
      this.filterOptions.page = 1;
      this.getPlanets();
    }
  }

  clearFilters($event: any): void {
    this.filterOptions = {
      page: 1,
      name: '',
      size: this.FIXED_SIZE,
      sort: '',
      direction: ''
    }
    this.lastPage = 1;
  }

  private getPlanets(): void {
    this.planetService.query(this.filterOptions).subscribe({
      next: (res) => {
        if (res && res.body) {
          this.planets = res.body.content;
          this.totalRecords = res.body.totalElements;
          this.lastPage = this.filterOptions.page!;
          this.loadingTable = false;
          this.loadingBtn = false;
        }
      }
    });
  }
}
