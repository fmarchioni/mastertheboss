import { color } from '@vaadin/vaadin-lumo-styles/color.js';
import { typography } from '@vaadin/vaadin-lumo-styles/typography.js';

const tpl = document.createElement('template');
tpl.innerHTML = `<style>
  ${color.cssText}
  ${typography.cssText}
</style>`;
document.head.appendChild(tpl.content);
