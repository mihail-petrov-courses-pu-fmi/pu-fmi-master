import { Component, inject, OnInit } from "@angular/core";
import { ActivatedRoute, ActivatedRouteSnapshot } from "@angular/router";
import { CustomerService } from "../../services/customer.service";
import { DataGridComponent, DataGridHeader } from "../../components/data-grid/data-grid.component";
import { FormsModule } from "@angular/forms";
import { ProjectService } from "../../services/project.service";

@Component({
  standalone  : true,
  templateUrl : './project.component.html',
  styleUrl    : './project.component.css',
  imports     : [
    FormsModule,
    DataGridComponent
  ]})
export class ProjectPage implements OnInit {

  public state = {
    isCreateNewProjectVisible : false
  }

  public project = {
    name        : '',
    cost        : 0,
    customerId  : -1
  }

  public projectCollection = [];

  public headerConfig: DataGridHeader[] = [
    { column  : 'Project name', value: 'name' },
    { column  : 'Project cost', value: 'cost' }
  ]

  private activeRouter        = inject(ActivatedRoute)
  private customerServiceApi  = inject(CustomerService)
  private projectServiceApi   = inject(ProjectService);


  public ngOnInit(): void {
    this.fetchProjectCollection();
  }

  public fetchProjectCollection() {

    // когато отворя СТРАНИЦАТА, да взема конкретно парче от URL
    const customerId = this.activeRouter.snapshot.paramMap.get('id');

    if(customerId) {

      this.project.customerId = +customerId;

      this.customerServiceApi.getAllProjects(+customerId).subscribe((response: any) => {
        this.projectCollection = response.data;
      })
    }
  }

  public processOnSave() {
    this.projectServiceApi.createNew(this.project).subscribe(() => {
      this.fetchProjectCollection();
    })
  }
}
