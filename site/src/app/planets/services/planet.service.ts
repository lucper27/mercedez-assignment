import { HttpClient, HttpResponse } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { FilterParamsService } from '../../shared/utils/filter-params.service';
import { ICommonFilter } from '../../shared/models/filter.model';
import { PaginatedResponse } from '../../shared/models/paginated-response.model';
import { IPlanet } from '../models/planet.model';
import { Observable } from 'rxjs';

type PlanetResponseType = HttpResponse<PaginatedResponse<IPlanet>>;


@Injectable({
  providedIn: 'root'
})
export class PlanetService {

  private readonly http = inject(HttpClient);

  private readonly filterParamsService = inject(FilterParamsService);

  resourceUrl = 'http://localhost:8080/api/planets';

  constructor() { }

    query(options?: ICommonFilter): Observable<PlanetResponseType>{
      const params = this.filterParamsService.convertOptionsIntoParams(options);
      return this.http.get<PaginatedResponse<IPlanet>>(this.resourceUrl, {params: params, observe: 'response'});
    }
}
