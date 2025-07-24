import { HttpClient, HttpResponse } from '@angular/common/http';
import { Inject, inject, Injectable } from '@angular/core';
import { PaginatedResponse } from '../../shared/models/paginated-response.model';
import { IPeople } from '../models/people.model';
import { ICommonFilter } from '../../shared/models/filter.model';
import { Observable } from 'rxjs';
import { FilterParamsService } from '../../shared/utils/filter-params.service';
import { environment } from '../../../environments/environment';

type PeopleResponseType = HttpResponse<PaginatedResponse<IPeople>>;

@Injectable({
  providedIn: 'root'
})
export class PeopleService {

  private readonly http = inject(HttpClient);

  private readonly filterParamsService = inject(FilterParamsService);

  constructor(@Inject('API_BASE_URL') private readonly baseUrl: string) {}

  query(options?: ICommonFilter): Observable<PeopleResponseType> {
    const params = this.filterParamsService.convertOptionsIntoParams(options);
    const url = `${this.baseUrl}/people`;
    return this.http.get<PaginatedResponse<IPeople>>(url, {
      params,
      observe: 'response'
    });
  }

  
}
