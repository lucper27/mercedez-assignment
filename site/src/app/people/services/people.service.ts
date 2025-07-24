import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { PaginatedResponse } from '../../shared/models/paginated-response.model';
import { IPeople } from '../models/people.model';
import { ICommonFilter } from '../../shared/models/filter.model';
import { Observable } from 'rxjs';
import { FilterParamsService } from '../../shared/utils/filter-params.service';

type PeopleResponseType = HttpResponse<PaginatedResponse<IPeople>>;

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  private readonly http = inject(HttpClient);

  private readonly filterParamsService = inject(FilterParamsService);

  resourceUrl = 'http://localhost:8080/api/people';

  constructor() { }

  query(options?: ICommonFilter): Observable<PeopleResponseType>{
    const params = this.filterParamsService.convertOptionsIntoParams(options);
    return this.http.get<PaginatedResponse<IPeople>>(this.resourceUrl, {params: params, observe: 'response'});
  }

  
}
