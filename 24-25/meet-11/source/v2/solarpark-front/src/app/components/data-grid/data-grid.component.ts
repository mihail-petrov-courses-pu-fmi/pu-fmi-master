import { Component, EventEmitter, Input, OnChanges, Output, SimpleChanges } from "@angular/core";

export type DataGridHeader = {
  column: string,
  value: string
}

@Component({
  standalone  : true,
  selector    : 'cc-data-grid',
  templateUrl : './data-grid.component.html',
  styleUrl    : './data-grid.component.css'
})
export class DataGridComponent implements OnChanges {

  // Input
  @Input() public inputHeaderConfig: DataGridHeader[] = [];
  @Input() public inputDataSource: any;

  @Input() public inputIsEditable    = false;
  @Input() public inputIsRemovable   = false;
  @Input() public inputIsNavigatable = false;


  // Output - потребителски дефинирани събития
  @Output()
  public onEdit = new EventEmitter();

  @Output()
  public onRemove = new EventEmitter();

  @Output()
  public onNavigate = new EventEmitter();

  ngOnChanges(changes: SimpleChanges): void {
    console.log("@@")
    console.log(this.inputDataSource);
  }

}
