/*!CK:4040485957!*//*1438197740,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["FNImg"]); }

__d("VideoWithClickPlayPause",["VideoPlayerReason","logVideosClickTracking"],function(a,b,c,d,e,f,g,h){b.__markCompiled&&b.__markCompiled();function i(j){"use strict";this.$VideoWithClickPlayPause0=j;this.$VideoWithClickPlayPause1=this.$VideoWithClickPlayPause0.getVideoNode();this.$VideoWithClickPlayPause0.addListener('clickVideo',this.$VideoWithClickPlayPause2.bind(this));}i.prototype.$VideoWithClickPlayPause2=function(){"use strict";if(this.$VideoWithClickPlayPause0.isState('playing')){this.$VideoWithClickPlayPause0.pause(g.USER);}else{h(this.$VideoWithClickPlayPause1);this.$VideoWithClickPlayPause0.play(g.USER);}};e.exports=i;},null);