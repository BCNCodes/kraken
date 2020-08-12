import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {STORAGE_NODE_BUTTONS, StorageNodeComponent} from './storage-node.component';
import {StorageTreeControlService} from 'projects/storage/src/lib/storage-tree/storage-tree-control.service';
import {storageTreeControlServiceSpy} from 'projects/storage/src/lib/storage-tree/storage-tree-control.service.spec';
import {StorageNodeButtonsComponent} from 'projects/storage/src/lib/storage-menu/storage-node-buttons/storage-node-buttons.component';
import {testStorageFileNode} from 'projects/storage/src/lib/entities/storage-node.spec';

describe('StorageNodeComponent', () => {
  let component: StorageNodeComponent;
  let fixture: ComponentFixture<StorageNodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StorageNodeComponent],
      providers: [
        {provide: StorageTreeControlService, useValue: storageTreeControlServiceSpy()},
        {provide: STORAGE_NODE_BUTTONS, useValue: StorageNodeButtonsComponent},
      ]
    })
      .overrideTemplate(StorageNodeComponent, '')
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StorageNodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set and get node', () => {
    const node = testStorageFileNode();
    component.node = node;
    expect(component.hasChild).toBeFalse();
    expect(component.node).toBe(node);
  });

  it('should set and get expanded', () => {
    component.expanded = true;
    expect(component.expanded).toBeTrue();
  });
});
