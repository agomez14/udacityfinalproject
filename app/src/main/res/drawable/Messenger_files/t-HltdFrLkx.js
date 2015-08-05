/*!CK:2398547512!*//*1432694058,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["WLvgd"]); }

__d("DimensionTracking",["Cookie","DOMDimensions","Event","debounce","isInIframe"],function(a,b,c,d,e,f,g,h,i,j,k){b.__markCompiled&&b.__markCompiled();function l(){var m=h.getViewportDimensions();g.set('wd',m.width+'x'+m.height);}if(!k()){setTimeout(l,100);i.listen(window,'resize',j(l,250));i.listen(window,'focus',l);}},null);