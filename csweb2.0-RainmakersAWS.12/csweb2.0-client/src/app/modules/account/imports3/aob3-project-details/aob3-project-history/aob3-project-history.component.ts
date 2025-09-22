import { Component, OnChanges, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ImportService } from '@app/core/services/account/import.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-aob3-project-history',
  templateUrl: './aob3-project-history.component.html',
  styleUrls: ['./aob3-project-history.component.scss']
})
export class Aob3ProjectHistoryComponent implements OnInit {

  public projectDetails: any;
  public dataForDetailsTable: any[] = [];
  public projectId: string;  
  displayedColumns: string[] = ['step', 'activity', 'status', 'startTime', 'endTime'];

  constructor(private route: ActivatedRoute, public importService: ImportService, private location: Location, private router: Router) { }

  ngOnInit() {
    this.projectId = this.route.snapshot.paramMap.get('projectId')!;
    console.log('Project ID:', this.projectId);
    this.getDetails(this.projectId);
  }

  getDetails(projectId: string) {
    this.importService.getProjectDetails(projectId).subscribe(
      (data: any) => {
        this.projectDetails = data;
        console.log('Project Details:', this.projectDetails);
        this.dataForDetailsTable = data.activityHistory || [];
        console.log('Activity History:', this.dataForDetailsTable);
      },
      (error: any) => {
        console.error('Error fetching project details:', error);
      }
    );
  }
  
}

