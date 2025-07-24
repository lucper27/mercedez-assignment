import { Component, inject } from '@angular/core';
import { DataTableComponent } from "../../shared/components/data-table/data-table.component";
import { PeopleService } from '../services/people.service';
import { IPeople } from '../models/people.model';
import { ICommonFilter } from '../../shared/models/filter.model';
import { FIXED_SIZE } from '../../shared/constants/pagination.constants';


@Component({
  selector: 'people-list',
  standalone: true,
  imports: [DataTableComponent],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class PeopleListComponent {

  private readonly peopleService = inject(PeopleService);

  people?: IPeople[];

  FIXED_SIZE = FIXED_SIZE;

  columns = [
    { field: 'name', header: 'Name' },
    { field: 'gender', header: 'Gender' },
    { field: 'birthYear', header: 'Birth Year' },
    { field: 'eyeColor', header: 'Eye Color' },
    { field: 'hairColor', header: 'Hair Color' },
    { field: 'height', header: 'Height' },
    { field: 'mass', header: 'Mass' },
    { field: 'skinColor', header: 'Skin Color' },
    { field: 'homeworld', header: 'Homeworld' },
    { field: 'created', header: 'Created' },
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


  ngOnInit(): void {
  }

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
    this.getPeople();
  }

  onNameSearch(name: string): void {
    if (name !== '') {
      this.filterOptions.name = name;
      this.loadingTable = true;
      this.loadingBtn = true;
      this.filterOptions.page = 1;
      this.getPeople();
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


  private getPeople(): void {
    this.peopleService.query(this.filterOptions).subscribe({
      next: (res) => {
        if (res && res.body) {
          this.people = res.body.content;
          this.totalRecords = res.body.totalElements;
          this.loadingTable = false;
          this.loadingBtn = false;
        }
      }
    });
  }
}
