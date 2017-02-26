import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, AbstractControl, ValidatorFn, FormArray } from '@angular/forms';
import 'rxjs/add/operator/debounceTime';
import { Router } from '@angular/router';
import { ActivityService } from './activities.service';
import { Project } from './activities.model';

@Component({
  moduleId: module.id,
  selector: 'view-projects',
  templateUrl :'viewprojects.html',
  styleUrls:['style.css'],
  providers:[ ActivityService ]
})
export class ViewProjectsComponent implements OnInit {

constructor(private activityService: ActivityService){}

	pageTitle: string = 'Projects';
	projects : Project[] = [];
	errorMessage: string;

	ngOnInit():void {
		this.activityService.listProjects().subscribe(projectlist => this.projects = projectlist,
                           error => this.errorMessage = <any>error);   
	}
}