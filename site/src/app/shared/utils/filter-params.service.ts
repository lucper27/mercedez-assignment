import { Injectable } from '@angular/core';
import { ICommonFilter } from '../models/filter.model';

@Injectable({
  providedIn: 'root'
})
export class FilterParamsService {

  constructor() { }

  convertOptionsIntoParams(options?: ICommonFilter): any {
      let result: any = {
        page: 1,
        name: '',
        size: 15,
        sort: '',
        direction: ''
      };
      if (options) {
        if (options.page) {
          result.page = options.page;
        }
        if (options.name) {
          result.name = options.name
        }
        if (options.size) {
          result.size = options.size;
        }
        if (options.sort) {
          result.sort = options.sort;
        }
        if (options.direction) {
          result.direction = options.direction;
        }
      }
      return result;
    }
}
