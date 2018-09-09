import mixins from './mixins/index.js';
import { Bar, HorizontalBar, Doughnut, Line, Pie, PolarArea, Radar, Bubble, Scatter, generateChart } from './BaseCharts';
var VueCharts = {
  Bar: Bar,
  HorizontalBar: HorizontalBar,
  Doughnut: Doughnut,
  Line: Line,
  Pie: Pie,
  PolarArea: PolarArea,
  Radar: Radar,
  Bubble: Bubble,
  Scatter: Scatter,
  mixins: mixins,
  generateChart: generateChart
};
export default VueCharts;
export { VueCharts, Bar, HorizontalBar, Doughnut, Line, Pie, PolarArea, Radar, Bubble, Scatter, mixins, generateChart };