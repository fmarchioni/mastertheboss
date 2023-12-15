/**
 * @license
 * Copyright 2000-2023 Vaadin Ltd.
 *
 * This program is available under Vaadin Commercial License and Service Terms.
 *
 * See <https://vaadin.com/commercial-license-and-service-terms> for the full
 * license.
 */
import Feature from 'ol/Feature';
import Point from 'ol/geom/Point';
import View from 'ol/View';
import { synchronizeImageLayer, synchronizeTileLayer, synchronizeVectorLayer } from './layers.js';
import {
  synchronizeImageWMSSource,
  synchronizeOSMSource,
  synchronizeTileWMSSource,
  synchronizeVectorSource,
  synchronizeXYZSource
} from './sources.js';
import { synchronizeIcon, synchronizeFill, synchronizeStroke, synchronizeStyle } from './styles.js';
import { convertToCoordinateArray, synchronizeCollection } from './util.js';

function synchronizeMap(target, source, context) {
  if (!target) {
    throw new Error('Existing map instance must be provided');
  }

  synchronizeCollection(target.getLayers(), source.layers, context);
  target.setView(context.lookup.get(source.view));

  return target;
}

function synchronizeView(target, source, _context) {
  if (!target) {
    target = new View({
      projection: source.projection
    });
  }

  target.setCenter(source.center ? convertToCoordinateArray(source.center) : [0, 0]);
  target.setRotation(source.rotation || 0);
  target.setZoom(source.zoom || 0);

  return target;
}

function synchronizePoint(target, source, _context) {
  if (!target) {
    target = new Point(convertToCoordinateArray(source.coordinates));
  }

  target.setCoordinates(convertToCoordinateArray(source.coordinates));

  return target;
}

function synchronizeFeature(target, source, context) {
  if (!target) {
    target = new Feature();
  }

  target.setGeometry(context.lookup.get(source.geometry));
  target.setStyle(context.lookup.get(source.style));

  return target;
}

const synchronizerLookup = {
  'ol/Feature': synchronizeFeature,
  'ol/Map': synchronizeMap,
  'ol/View': synchronizeView,
  // Layers
  'ol/layer/Image': synchronizeImageLayer,
  'ol/layer/Tile': synchronizeTileLayer,
  'ol/layer/Vector': synchronizeVectorLayer,
  // Sources
  'ol/source/ImageWMS': synchronizeImageWMSSource,
  'ol/source/OSM': synchronizeOSMSource,
  'ol/source/TileWMS': synchronizeTileWMSSource,
  'ol/source/Vector': synchronizeVectorSource,
  'ol/source/XYZ': synchronizeXYZSource,
  // Geometry
  'ol/geom/Point': synchronizePoint,
  // Styles
  'ol/style/Icon': synchronizeIcon,
  'ol/style/Fill': synchronizeFill,
  'ol/style/Stroke': synchronizeStroke,
  'ol/style/Style': synchronizeStyle
};

/**
 * Synchronizes a configuration object into a corresponding OpenLayers class
 * instance. All objects are expected to have:
 * - a type property to specify which OpenLayers class / type to use
 * - an ID property to identify the instance in future syncs
 *
 * The function uses a lookup map to retrieve the OL instance that should be
 * synchronized into by the object's unique ID. If an instance for that ID
 * does not exist yet, it will be created by the type-specific synchronization
 * function and then stored in the lookup for later synchronizations.
 *
 * Only specific OpenLayers classes are supported for synchronization.
 *
 * @param updatedObject The configuration object to synchronize from
 * @param context The map-specific context for the synchronization
 * @returns {*}
 */
export function synchronize(updatedObject, context) {
  const type = updatedObject.type;

  if (!type) {
    throw new Error('Configuration object must have a type');
  }
  if (!updatedObject.id) {
    throw new Error('Configuration object must have an ID');
  }

  let instance = context.lookup.get(updatedObject.id);

  const synchronizer = synchronizerLookup[type];
  if (!synchronizer) {
    throw new Error(`Unsupported configuration object type: ${type}`);
  }

  // Call the type-specific synchronizer function to either create a new
  // OpenLayers instance, or update the existing one
  instance = synchronizer(instance, updatedObject, context);

  context.lookup.put(updatedObject.id, instance);

  // Store id on synchronized instance
  instance.id = updatedObject.id;
  // Store type name on sync result for type checks in tests
  instance.typeName = type;

  return instance;
}
