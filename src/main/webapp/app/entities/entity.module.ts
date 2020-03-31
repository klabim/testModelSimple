import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'wa-employee',
        loadChildren: () => import('./wa-employee/wa-employee.module').then(m => m.TestModelSimpleWaEmployeeModule)
      },
      {
        path: 'wa-gender',
        loadChildren: () => import('./wa-gender/wa-gender.module').then(m => m.TestModelSimpleWaGenderModule)
      },
      {
        path: 'wa-nationality',
        loadChildren: () => import('./wa-nationality/wa-nationality.module').then(m => m.TestModelSimpleWaNationalityModule)
      },
      {
        path: 'wa-marital-status',
        loadChildren: () => import('./wa-marital-status/wa-marital-status.module').then(m => m.TestModelSimpleWaMaritalStatusModule)
      },
      {
        path: 'wa-personal-address',
        loadChildren: () => import('./wa-personal-address/wa-personal-address.module').then(m => m.TestModelSimpleWaPersonalAddressModule)
      },
      {
        path: 'wa-assignment',
        loadChildren: () => import('./wa-assignment/wa-assignment.module').then(m => m.TestModelSimpleWaAssignmentModule)
      },
      {
        path: 'wa-host-company',
        loadChildren: () => import('./wa-host-company/wa-host-company.module').then(m => m.TestModelSimpleWaHostCompanyModule)
      },
      {
        path: 'wa-home-company',
        loadChildren: () => import('./wa-home-company/wa-home-company.module').then(m => m.TestModelSimpleWaHomeCompanyModule)
      },
      {
        path: 'wa-job',
        loadChildren: () => import('./wa-job/wa-job.module').then(m => m.TestModelSimpleWaJobModule)
      },
      {
        path: 'wa-classification',
        loadChildren: () => import('./wa-classification/wa-classification.module').then(m => m.TestModelSimpleWaClassificationModule)
      },
      {
        path: 'wa-management-hris',
        loadChildren: () => import('./wa-management-hris/wa-management-hris.module').then(m => m.TestModelSimpleWaManagementHrisModule)
      },
      {
        path: 'managerial-link',
        loadChildren: () => import('./managerial-link/managerial-link.module').then(m => m.TestModelSimpleManagerialLinkModule)
      },
      {
        path: 'wa-seniority',
        loadChildren: () => import('./wa-seniority/wa-seniority.module').then(m => m.TestModelSimpleWaSeniorityModule)
      },
      {
        path: 'wa-language',
        loadChildren: () => import('./wa-language/wa-language.module').then(m => m.TestModelSimpleWaLanguageModule)
      },
      {
        path: 'fixed-compensation',
        loadChildren: () => import('./fixed-compensation/fixed-compensation.module').then(m => m.TestModelSimpleFixedCompensationModule)
      },
      {
        path: 'variable-compensation',
        loadChildren: () =>
          import('./variable-compensation/variable-compensation.module').then(m => m.TestModelSimpleVariableCompensationModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class TestModelSimpleEntityModule {}
