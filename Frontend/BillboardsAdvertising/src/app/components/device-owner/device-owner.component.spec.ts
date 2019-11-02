import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceOwnerComponent } from './device-owner.component';

describe('DeviceOwnerComponent', () => {
  let component: DeviceOwnerComponent;
  let fixture: ComponentFixture<DeviceOwnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeviceOwnerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeviceOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
