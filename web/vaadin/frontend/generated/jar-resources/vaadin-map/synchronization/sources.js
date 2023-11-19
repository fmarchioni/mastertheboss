/**
 * @license
 * Copyright 2000-2023 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
import Collection from 'ol/Collection';
import ImageWMS from 'ol/source/ImageWMS';
import OSM, { ATTRIBUTION as OSM_ATTRIBUTION } from 'ol/source/OSM';
import TileWMS from 'ol/source/TileWMS';
import VectorSource from 'ol/source/Vector';
import XYZ from 'ol/source/XYZ';
import { createOptions, synchronizeCollection } from './util.js';

function synchronizeSource(target, source, _context) {
  if (!target) {
    throw new Error('Can not instantiate base class: ol/source/Source');
  }

  target.setAttributions(source.attributions);

  return target;
}

function synchronizeTileSource(target, source, context) {
  if (!target) {
    throw new Error('Can not instantiate base class: ol/source/Tile');
  }
  synchronizeSource(target, source, context);

  return target;
}

function synchronizeUrlTileSource(target, source, context) {
  if (!target) {
    throw new Error('Can not instantiate base class: ol/source/UrlTile');
  }
  synchronizeTileSource(target, source, context);
  // Setting null URL is not supported. While not an actual use-case, it is useful to prevent errors here in order
  // to keep the URL empty in integration tests
  if (source.url) {
    target.setUrl(source.url);
  }

  return target;
}

function synchronizeTileImageSource(target, source, context) {
  if (!target) {
    throw new Error('Can not instantiate base class: ol/source/TileImage');
  }
  synchronizeUrlTileSource(target, source, context);

  return target;
}

export function synchronizeTileWMSSource(target, source, context) {
  if (!target) {
    target = new TileWMS(createOptions(source));
  }
  synchronizeTileImageSource(target, source, context);

  return target;
}

export function synchronizeXYZSource(target, source, context) {
  if (!target) {
    target = new XYZ(createOptions(source));
  }
  synchronizeTileImageSource(target, source, context);

  return target;
}

export function synchronizeOSMSource(target, source, context) {
  if (!target) {
    target = new OSM(createOptions(source));
  }

  // For OSM source use default attributions as fallback
  if (!source.attributions) {
    source.attributions = OSM_ATTRIBUTION;
  }
  synchronizeXYZSource(target, source, context);

  return target;
}

function synchronizeImageSource(target, source, context) {
  if (!target) {
    throw new Error('Can not instantiate base class: ol/source/Image');
  }
  synchronizeSource(target, source, context);

  return target;
}

export function synchronizeImageWMSSource(target, source, context) {
  if (!target) {
    target = new ImageWMS(createOptions(source));
  }
  synchronizeImageSource(target, source, context);
  // Setting null URL is not supported. While not an actual use-case, it is useful to prevent errors here in order
  // to keep the URL empty in integration tests
  if (source.url) {
    target.setUrl(source.url);
  }

  return target;
}

export function synchronizeVectorSource(target, source, context) {
  if (!target) {
    target = new VectorSource(
      createOptions({
        ...source,
        features: new Collection()
      })
    );
  }
  synchronizeSource(target, source, context);
  synchronizeCollection(target.getFeaturesCollection(), source.features, context);

  return target;
}
