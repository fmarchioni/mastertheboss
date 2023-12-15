/**
 * @license
 * Copyright 2000-2023 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
import ImageLayer from 'ol/layer/Image';
import TileLayer from 'ol/layer/Tile';
import VectorLayer from 'ol/layer/Vector';
import { createOptions } from './util.js';

function synchronizeLayer(target, source, _context) {
  if (!target) {
    throw new Error('Can not instantiate base class: ol/layer/Layer');
  }

  target.setOpacity(source.opacity);
  target.setVisible(source.visible);
  target.setZIndex(source.zIndex || undefined);
  target.setMinZoom(source.minZoom || -Infinity);
  target.setMaxZoom(source.maxZoom || Infinity);
  target.setBackground(source.background || undefined);

  return target;
}

export function synchronizeTileLayer(target, source, context) {
  if (!target) {
    target = new TileLayer(
      createOptions({
        ...source,
        source: context.lookup.get(source.source)
      })
    );
  }

  synchronizeLayer(target, source);
  target.setSource(context.lookup.get(source.source));

  return target;
}

export function synchronizeVectorLayer(target, source, context) {
  if (!target) {
    target = new VectorLayer(
      createOptions({
        ...source,
        source: context.lookup.get(source.source)
      })
    );
  }

  synchronizeLayer(target, source);
  target.setSource(context.lookup.get(source.source));

  return target;
}

export function synchronizeImageLayer(target, source, context) {
  if (!target) {
    target = new ImageLayer(
      createOptions({
        ...source,
        source: context.lookup.get(source.source)
      })
    );
  }

  synchronizeLayer(target, source);
  target.setSource(context.lookup.get(source.source));

  return target;
}
