/*!CK:1466561654!*//*1438197740,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["VJtEK"]); }

__d("VideosRenderingInstrumentation",["DataStore"],function(a,b,c,d,e,f,g){b.__markCompiled&&b.__markCompiled();var h={storeRenderTime:function(i){var j=Date.now();g.set(i,'videos_rendering_instrumentation',j);return j;},retrieveRenderTime:function(i){var j=g.get(i,'videos_rendering_instrumentation',NaN);if(Number.isNaN(j))j=h.storeRenderTime(i);return j;}};e.exports=h;},null);