import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output, ViewChild } from '@angular/core';
import { Table, TableModule } from 'primeng/table';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-data-table',
  standalone: true,
  imports: [TableModule, CommonModule, FormsModule, ButtonModule],
  templateUrl: './data-table.component.html',
  styleUrl: './data-table.component.scss'
})
export class DataTableComponent {
  @ViewChild('dataTable') dataTable!: Table;


  @Input() data: any[] = [];
  @Input() columns: { field: string; header: string }[] = [];
  @Input() totalRecords: number = 0;
  @Input() rows: number = 15;
  @Input() loadingTable: boolean = false;
  @Input() loadingBtn: boolean = false;
  @Output() lazyChange = new EventEmitter<{
  page: number;
  sort?: { field: string; order: number };
  }>();
  @Output() nameSearch = new EventEmitter<string>();
  @Output() clearFilters = new EventEmitter<boolean>();

  name: string = '';

  onLazyLoadHandler(event: any): void {
  const page = Math.floor(event.first / event.rows) + 1;

  const sort = event.sortField
    ? { field: event.sortField, order: event.sortOrder }
    : undefined;

  this.lazyChange.emit({ page, sort });
  }

  onClick(): void {
    if (this.name !== '') {
    this.loadingBtn = true;
    this.nameSearch.emit(this.name);
    }

  }

  onClearFiltersClick() {
    this.name = '';
    this.clearFilters.emit();
    this.loadingTable = true;
    this.data = [];
    this.dataTable.reset();
    
  }
}
