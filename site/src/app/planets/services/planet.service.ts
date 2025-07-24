import { HttpClient, HttpResponse } from '@angular/common/http';
import { Inject, inject, Injectable } from '@angular/core';
import { FilterParamsService } from '../../shared/utils/filter-params.service';
import { ICommonFilter } from '../../shared/models/filter.model';
import { PaginatedResponse } from '../../shared/models/paginated-response.model';
import { IPlanet } from '../models/planet.model';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';

type PlanetResponseType = HttpResponse<PaginatedResponse<IPlanet>>;


@Injectable({
  providedIn: 'root'
})
export class PlanetService {

  private readonly http = inject(HttpClient);

  private readonly filterParamsService = inject(FilterParamsService);

  constructor(@Inject('API_BASE_URL') private readonly baseUrl: string) {}

    query(options?: ICommonFilter): Observable<PlanetResponseType>{
      const params = this.filterParamsService.convertOptionsIntoParams(options);
      const url = `${this.baseUrl}/planets`;
      return this.http.get<PaginatedResponse<IPlanet>>(url, {params: params, observe: 'response'});
    }
}
