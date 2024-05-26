import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurrencyTabsComponent } from './currency-tabs.component';

describe('CurrencyTabsComponent', () => {
  let component: CurrencyTabsComponent;
  let fixture: ComponentFixture<CurrencyTabsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CurrencyTabsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurrencyTabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
