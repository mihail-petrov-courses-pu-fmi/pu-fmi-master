import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { ProjectType } from "../models/project.model";
import { environment } from "../environments/environment";

@Injectable({
  providedIn: "root"
})
export class ProjectService {

  private http    = inject(HttpClient)
  private baseUrl = `${environment.baseUrl}/projects`;;

  public createNew($project: ProjectType) {
    return this.http.post(this.baseUrl, $project);
  }
}
