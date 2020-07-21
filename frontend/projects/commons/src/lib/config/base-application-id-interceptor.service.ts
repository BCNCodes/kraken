import {Injectable} from '@angular/core';
import {ConfigurationService} from 'projects/commons/src/lib/config/configuration.service';
import {ApplicationIdHeaderInterceptor} from 'projects/commons/src/lib/config/application-id-header-interceptor.service';

@Injectable()
export class BaseApplicationIdInterceptorService extends ApplicationIdHeaderInterceptor {

  constructor(configuration: ConfigurationService) {
    super(configuration, () => [configuration.backendApiUrl]);
  }

}
